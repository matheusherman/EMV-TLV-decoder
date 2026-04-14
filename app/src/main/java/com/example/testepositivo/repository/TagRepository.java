package com.example.testepositivo.repository;

import com.example.testepositivo.model.BitDefinition;
import com.example.testepositivo.model.ByteDefinition;
import com.example.testepositivo.model.TagDefinition;

import java.util.Arrays;

public class TagRepository {

    public static TagDefinition getTagDefinition(String tag) {
        if (tag.equals("9F33")) {
            return create9F33();

        }
        else if (tag.equals("9F6C")) {
            return create9F6C();
        }
        return null;
    }

    private static TagDefinition create9F33() {

        // Byte 1 - Card Data Input Capability
        ByteDefinition byte1 = new ByteDefinition(
                1,
                Arrays.asList(
                        new BitDefinition(8, "Manual key entry"),
                        new BitDefinition(7, "Magnetic stripe"),
                        new BitDefinition(6, "IC with contacts")
                )
        );

        // Byte 2 - CVM Capability
        ByteDefinition byte2 = new ByteDefinition(
                2,
                Arrays.asList(
                        new BitDefinition(8, "Plaintext PIN for ICC verification"),
                        new BitDefinition(7, "Enciphered PIN for online verification"),
                        new BitDefinition(6, "Signature (paper)"),
                        new BitDefinition(5, "Enciphered PIN for offline verification"),
                        new BitDefinition(4, "No CVM Required")
                )
        );

        // Byte 3 - Security Capability
        ByteDefinition byte3 = new ByteDefinition(
                3,
                Arrays.asList(
                        new BitDefinition(8, "SDA"),
                        new BitDefinition(7, "DDA"),
                        new BitDefinition(6, "Card capture"),
                        new BitDefinition(4, "CDA")
                )
        );

        return new TagDefinition(
                "9F33",
                Arrays.asList(byte1, byte2, byte3)
        );
    }

    private static TagDefinition create9F6C() {

        // Byte 1
        ByteDefinition byte1 = new ByteDefinition(
                1,
                Arrays.asList(
                        new BitDefinition(8, "Online PIN Required"),
                        new BitDefinition(7, "Signature Required"),
                        new BitDefinition(6, "Go Online if Offline Data Authentication Fails"),
                        new BitDefinition(5, "Switch Interface if Offline Data Authentication Fails"),
                        new BitDefinition(4, "Go Online if Application Expired"),
                        new BitDefinition(3, "Switch Interface for Cash Transactions"),
                        new BitDefinition(2, "Switch Interface for Cashback Transactions")
                )
        );

        // Byte 2
        ByteDefinition byte2 = new ByteDefinition(
                2,
                Arrays.asList(
                        new BitDefinition(8, "Consumer Device CVM Performed"),
                        new BitDefinition(7, "Card supports Issuer Update Processing at the POS")
                )
        );

        return new TagDefinition(
                "9F6C",
                Arrays.asList(byte1, byte2)
        );
    }
}

