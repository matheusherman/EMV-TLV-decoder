package com.example.testepositivo.model;

public class BitDefinition {

    private int bitNumber;

    private String description;

    public BitDefinition(int bitNumber, String description) {
        this.bitNumber = bitNumber;
        this.description = description;
    }

    public int getBitNumber() {
        return bitNumber;
    }

    public String getDescription() {
        return description;
    }
}
