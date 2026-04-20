package com.example.testepositivo;

import org.junit.Test;
import static org.junit.Assert.*;

public class TLVParserTest {

    // Happy path

    @Test
    public void shouldConvertHexToBytes() {
        byte[] result = TLVParser.hexToBytes("A0B1");

        assertEquals(2, result.length);
        assertEquals((byte) 0xA0, result[0]);
        assertEquals((byte) 0xB1, result[1]);
    }

    @Test
    public void shouldHandleLowercase() {
        byte[] result = TLVParser.hexToBytes("a0");

        assertEquals(1, result.length);
        assertEquals((byte) 0xA0, result[0]);
    }

    @Test
    public void shouldExtractValueFromTLV() {
        String value = TLVParser.extractValue("9F33", "9F3303A0B1C2");

        assertEquals("A0B1C2", value);
    }

    // Edge cases

    @Test(expected = RuntimeException.class)
    public void shouldThrowWhenLengthIsInvalid() {
        String value = TLVParser.extractValue("9F33", "9F3303A0B1");
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowWhenHexLengthIsOdd() {
        TLVParser.hexToBytes("A0B"); // tamanho ímpar
    }
}