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
        else if (tag.equals("9F6C")) {
            return create9F6C();
        }
        else if (tag.equals("82")) {
            return create82();
        }
        else if (tag.equals("9F66")) {
            return create9F66();
        }
        else if (tag.equals("9F40")) {
            return create9F40();
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
  
    private static TagDefinition create82() {

        // Byte 1
        ByteDefinition byte1 = new ByteDefinition(
                1,
                Arrays.asList(
                        new BitDefinition(6, "DDA supported (EMV mode)")
                )
        );

        // Byte 2
        ByteDefinition byte2 = new ByteDefinition(
                2,
                Arrays.asList(
                        new BitDefinition(8, "Mag-stripe mode supported"),
                        new BitDefinition(7, "Mobile phone"),
                        new BitDefinition(6, "Contactless transaction supported")
                )
        );
      
        return new TagDefinition(
            "82",
            Arrays.asList(byte1, byte2)
        );
    }

    private static TagDefinition create9F40() {

        // Byte 1 - Transaction Type Capability
        ByteDefinition byte1 = new ByteDefinition(
                1,
                Arrays.asList(
                        new BitDefinition(8, "Cash"),
                        new BitDefinition(7, "Goods"),
                        new BitDefinition(6, "Services"),
                        new BitDefinition(5, "Cashback"),
                        new BitDefinition(4, "Inquiry"),
                        new BitDefinition(3, "Transfer"),
                        new BitDefinition(2, "Payment"),
                        new BitDefinition(1, "Administrative")
                )
        );

        // Byte 2 - Transaction Type Capability
        ByteDefinition byte2 = new ByteDefinition(
                2,
                Arrays.asList(
                        new BitDefinition(8, "Cash Deposit")
                )
        );

        // Byte 3 - Terminal Data Input Capability
        ByteDefinition byte3 = new ByteDefinition(
                3,
                Arrays.asList(
                        new BitDefinition(8, "Numeric keys"),
                        new BitDefinition(7, "Alphabetic and special character keys"),
                        new BitDefinition(6, "Command keys"),
                        new BitDefinition(5, "Function keys")
                )
        );

        // Byte 4 - Terminal Data Output Capability
        ByteDefinition byte4 = new ByteDefinition(
                4,
                Arrays.asList(
                        new BitDefinition(8, "Print, attendant"),
                        new BitDefinition(7, "Print, cardholder"),
                        new BitDefinition(6, "Display, attendant"),
                        new BitDefinition(5, "Display, cardholder"),
                        new BitDefinition(2, "Code table 10"),
                        new BitDefinition(1, "Code table 9")
                )
        );

        // Byte 5 - Terminal Data Output Capability (Code tables)
        ByteDefinition byte5 = new ByteDefinition(
                5,
                Arrays.asList(
                        new BitDefinition(8, "Code table 8"),
                        new BitDefinition(7, "Code table 7"),
                        new BitDefinition(6, "Code table 6"),
                        new BitDefinition(5, "Code table 5"),
                        new BitDefinition(4, "Code table 4"),
                        new BitDefinition(3, "Code table 3"),
                        new BitDefinition(2, "Code table 2"),
                        new BitDefinition(1, "Code table 1")
                )
        );

        return new TagDefinition(
                "9F40",
                Arrays.asList(byte1, byte2, byte3, byte4, byte5)
        );
    }

    private static TagDefinition create9F66() {

        // Byte 1
        ByteDefinition byte1 = new ByteDefinition(
                1,
                Arrays.asList(
                        new BitDefinition(8, "Mag-stripe mode supported"),
                        new BitDefinition(6, "EMV mode supported"),
                        new BitDefinition(5, "EMV contact chip supported"),
                        new BitDefinition(4, "Offline-only reader"),
                        new BitDefinition(3, "Online PIN supported"),
                        new BitDefinition(2, "Signature supported"),
                        new BitDefinition(1, "Offline Data Authentication for Online Authorizations supported")
                        // bit 7 RFU
                )
        );

        // Byte 2
        ByteDefinition byte2 = new ByteDefinition(
                2,
                Arrays.asList(
                        new BitDefinition(8, "Online cryptogram required"),
                        new BitDefinition(7, "CVM required"),
                        new BitDefinition(6, "Offline PIN supported (contact)")
                        // bits 5-1 RFU
                )
        );

        // Byte 3
        ByteDefinition byte3 = new ByteDefinition(
                3,
                Arrays.asList(
                        new BitDefinition(8, "Issuer Update Processing supported"),
                        new BitDefinition(7, "Consumer Device CVM supported")
                        // bits 6-1 RFU
                )
        );

        // Byte 4 (todo RFU, não declarar)
        ByteDefinition byte4 = new ByteDefinition(
                4,
                Arrays.asList(
                        // tudo RFU → lista vazia
                )
        );

        return new TagDefinition(
                "9F66",
                Arrays.asList(byte1, byte2, byte3, byte4)
        );
    }
}

