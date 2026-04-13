package com.example.testepositivo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testepositivo.model.BitDefinition;
import com.example.testepositivo.model.ByteDefinition;
import com.example.testepositivo.model.TagDefinition;
import com.example.testepositivo.repository.TagRepository;

import br.com.positivo.emv.lib.EmvLib;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText inputId = findViewById(R.id.editTagInput);
        Button button = findViewById(R.id.btnBuscar);
        TextView TLV = findViewById(R.id.txtResultado);
        TextView txtBytes = findViewById(R.id.txtBytes);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String valor = inputId.getText().toString();

                String retorno = EmvLib.INSTANCE.getEmvTagInHexStringSync(valor);

                //chama funcao de tlv parser passando input e output

                String lengthHex = retorno.substring(valor.length(), valor.length()+2);
                int L = Integer.parseInt(lengthHex, 16);
                String V = retorno.substring( valor.length()+2);
                TLV.setText("T: " + valor + "\nL: " + L + "\nV: " + V);

                if (V.length() != L * 2) {
                    throw new RuntimeException("Erro de parser");
                }

                TagDefinition tagDef = TagRepository.getTagDefinition(valor);

                StringBuilder bytesFormatados = new StringBuilder();
                for(int i = 0; i<V.length(); i+=2) {
                    String byteHex = V.substring(i, i + 2);
                    //converter em binario
                    String binario = Integer.toBinaryString(Integer.parseInt(byteHex, 16));
                    String binario8bits = String.format("%8s", binario).replace(' ', '0');

                    bytesFormatados
                        .append(byteHex)
                        .append(" → ")
                        .append(binario8bits)
                        .append("\n");

                    ByteDefinition byteDef = tagDef.getBytes().get(i /2 );
                    for(int j = 0 ; j<binario8bits.length(); j++) {
                        char bit = binario8bits.charAt(j);
                        int numeroBit = 8 - j;
                        if (bit == '1') {

                            for (BitDefinition bitDef : byteDef.getBits()) {

                                if (bitDef.getBitNumber() == numeroBit) {

                                    bytesFormatados
                                            .append("  Bit ")
                                            .append(numeroBit)
                                            .append(": ")
                                            .append(bitDef.getDescription())
                                            .append("\n");
                                }
                            }
                        }
                    }
                    bytesFormatados.append("\n");
                }
                txtBytes.setText(bytesFormatados.toString());
            }
        });
    }
}