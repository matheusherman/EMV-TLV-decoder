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
//        else if (tag.equals("9F34")) {
//            return create9F34();
//        }
        else if (tag.equals("95")) {
            return create95();
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

    private static TagDefinition create95() {

        // Byte 1
        ByteDefinition byte1 = new ByteDefinition(
                1,
                Arrays.asList(
                        new BitDefinition(8, "Offline data authentication was not performed"),
                        new BitDefinition(7, "SDA failed"),
                        new BitDefinition(6, "ICC data missing"),
                        new BitDefinition(5, "Card appears on terminal exception file"),
                        new BitDefinition(4, "DDA failed"),
                        new BitDefinition(3, "CDA failed")
                        // bits 2-1 RFU
                )
        );

        // Byte 2
        ByteDefinition byte2 = new ByteDefinition(
                2,
                Arrays.asList(
                        new BitDefinition(8, "ICC and terminal have different application versions"),
                        new BitDefinition(7, "Expired application"),
                        new BitDefinition(6, "Application not yet effective"),
                        new BitDefinition(5, "Requested service not allowed for card product"),
                        new BitDefinition(4, "New card")
                        // bits 3-1 RFU
                )
        );

        // Byte 3
        ByteDefinition byte3 = new ByteDefinition(
                3,
                Arrays.asList(
                        new BitDefinition(8, "Cardholder verification was not successful"),
                        new BitDefinition(7, "Unrecognised CVM"),
                        new BitDefinition(6, "PIN Try Limit exceeded"),
                        new BitDefinition(5, "PIN entry required and PIN pad not present or not working"),
                        new BitDefinition(4, "PIN entry required, PIN pad present, but PIN was not entered"),
                        new BitDefinition(3, "Online PIN entered")
                        // bits 2-1 RFU
                )
        );

        // Byte 4
        ByteDefinition byte4 = new ByteDefinition(
                4,
                Arrays.asList(
                        new BitDefinition(8, "Transaction exceeds floor limit"),
                        new BitDefinition(7, "Lower consecutive offline limit exceeded"),
                        new BitDefinition(6, "Upper consecutive offline limit exceeded"),
                        new BitDefinition(5, "Transaction selected randomly for online processing"),
                        new BitDefinition(4, "Merchant forced transaction online")
                        // bits 3-1 RFU
                )
        );

        // Byte 5
        ByteDefinition byte5 = new ByteDefinition(
                5,
                Arrays.asList(
                        new BitDefinition(8, "Default TDOL used"),
                        new BitDefinition(7, "Issuer authentication failed"),
                        new BitDefinition(6, "Script processing failed before final GENERATE AC"),
                        new BitDefinition(5, "Script processing failed after final GENERATE AC")
                        // bits 4-1 RFU
                )
        );

        return new TagDefinition(
                "95",
                Arrays.asList(byte1, byte2, byte3, byte4, byte5)
        );
    }
}

