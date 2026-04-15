package com.example.testepositivo;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;

import com.example.testepositivo.model.BitDefinition;
import com.example.testepositivo.model.ByteDefinition;
import com.example.testepositivo.model.TagDefinition;
import com.example.testepositivo.repository.TagRepository;

public class BitAnalyzer {

    public static SpannableStringBuilder analyze(byte[] bytes, String tag) {

        SpannableStringBuilder sb = new SpannableStringBuilder();

        TagDefinition tagDef = TagRepository.getTagDefinition(tag);

        if (tagDef == null) {
            sb.append("Tag não mapeada\n");
            return sb;
        }

        // orquestração: só percorre bytes
        for (int i = 0; i < bytes.length; i++) {

            ByteDefinition byteDef = tagDef.getBytes().size() > i
                    ? tagDef.getBytes().get(i)
                    : null;

            analyzeByte(sb, bytes[i], i, byteDef);
        }

        return sb;
    }

    //percore os bytes
    private static void analyzeByte(SpannableStringBuilder sb, byte b, int index, ByteDefinition byteDef) {
        int byteNumber = index + 1;

        String binary = String.format("%8s",
                        Integer.toBinaryString(b & 0xFF))
                .replace(' ', '0');

        sb.append("Byte ")
                .append(String.valueOf(byteNumber))
                .append(" → ")
                .append(binary)
                .append("\n");

        for (int bit = 7; bit >= 0; bit--) {
            analyzeBit(sb, b, bit, byteDef);
        }

        sb.append("\n");
    }

    // percorre os bits
    private static void analyzeBit(SpannableStringBuilder sb, byte b, int bit, ByteDefinition byteDef) {
        int value = (b >> bit) & 1;
        int bitDisplay = bit + 1;

        int start = sb.length();

        sb.append("  Bit ")
                .append(String.valueOf(bitDisplay))
                .append(": ")
                .append(String.valueOf(value));

        BitDefinition bitDef = findBitDefinition(byteDef, bitDisplay);

        if (bitDef != null && value == 1) {

            sb.append(" → ").append(bitDef.getDescription());

            int end = sb.length();

            sb.setSpan(
                    new ForegroundColorSpan(Color.GREEN),
                    start,
                    end,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            );
        }

        sb.append("\n");
    }

    // busca no repository a descricao
    private static BitDefinition findBitDefinition(ByteDefinition byteDef, int bitNumber) {
        if (byteDef == null) return null;

        for (BitDefinition bit : byteDef.getBits()) {
            if (bit.getBitNumber() == bitNumber) {
                return bit;
            }
        }

        return null;
    }

}