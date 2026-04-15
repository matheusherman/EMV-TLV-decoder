package com.example.testepositivo;

public class TLVParser {

    public static byte[] hexToBytes(String hex) {
        int len = hex.length(); // tamanho do resultado
        byte[] data = new byte[len / 2]; // array de bytes

        for (int i = 0; i < len; i += 2) { // percorre de 2 a 2 ("byte por byte")
            data[i / 2] = (byte) (
                    (Character.digit(hex.charAt(i), 16) << 4) // converte o char da primeira posicao em hex base 16 e empurra 4 casas pra esquerda
                            + Character.digit(hex.charAt(i+1), 16) // converte a segunda posicao em hex base 16
            );
        }
        return data;
    }

    public static String formatHexBytes(String hex) { // separa o resultado em bytes
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < hex.length(); i += 2) {
            sb.append(hex, i, i + 2).append(" ");
        }

        return sb.toString().trim();
    }

    public static String extractValue(String tag, String hex) { // faz o parser do TLV extraindo o value

        int index = tag.length();

        String lengthHex = hex.substring(index, index + 2);
        int length = Integer.parseInt(lengthHex, 16);

        index += 2;

        String value = hex.substring(index);

        if (value.length() != length * 2) {
            throw new RuntimeException("Erro de parser");
        }

        return value;
    }
}
