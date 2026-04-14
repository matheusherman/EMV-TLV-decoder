package com.example.testepositivo;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText inputTag;
    private TextView outputHex;
    private TextView outputBits;

    private EmvService emvService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputTag = findViewById(R.id.inputTag);
        outputHex = findViewById(R.id.outputHex);
        outputBits = findViewById(R.id.outputBits);

        emvService = new EmvService();

        Button btnSync = findViewById(R.id.btnSync);
        Button btnAsync = findViewById(R.id.btnAsync);

        btnSync.setOnClickListener(v -> handleSync());
        btnAsync.setOnClickListener(v -> handleAsync());
    }

    private void handleSync() {
        String tag = inputTag.getText().toString().trim().toUpperCase();

        String hex = emvService.getTagSync(tag);
        String tagPart = tag;
        String lengthPart = hex.substring(tag.length(), tag.length() + 2);
        String valuePart = hex.substring(tag.length() + 2);

        // prints para log
        System.out.println("RETORNO BRUTO: " + hex);
        System.out.println("TAG: " + tagPart);
        System.out.println("LENGTH (hex): " + lengthPart);
        System.out.println("LENGTH (int): " + Integer.parseInt(lengthPart, 16));
        System.out.println("VALUE: " + valuePart);
        renderResult(tag, hex);
    }

    private void handleAsync() {
        String tag = inputTag.getText().toString().trim().toUpperCase();

        emvService.getTagAsync(tag, hex -> runOnUiThread(() -> {
            renderResult(tag, hex);
        }));
    }

    private void renderResult(String tag, String hex) {
        String formattedHex = TLVParser.formatHexBytes(hex);
        outputHex.setText("HEX: " + formattedHex);
        String value = TLVParser.extractValue(tag, hex);
        byte[] bytes = TLVParser.hexToBytes(value);

        outputBits.setText(BitAnalyzer.analyze(bytes, tag));
    }
}