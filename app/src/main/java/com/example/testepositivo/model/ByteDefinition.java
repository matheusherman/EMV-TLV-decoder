package com.example.testepositivo.model;

import java.util.List;

public class ByteDefinition {

    private int byteIndex;

     private List<BitDefinition> bits;

    public ByteDefinition(int byteIndex, List<BitDefinition> bits) {
        this.byteIndex = byteIndex;
        this.bits = bits;
    }

    public int getByteIndex() {
        return byteIndex;
    }

    public List<BitDefinition> getBits() {
        return bits;
    }
}
