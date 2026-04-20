package com.example.testepositivo;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 34) // sdk compativel com o Robolectric
public class BitAnalyzerTest {

    // happy path

    @Test
    public void shouldShowBinaryRepresentation() {
        byte[] bytes = new byte[]{(byte) 0b10100000};

        String result = BitAnalyzer.analyze(bytes, "9F33").toString();

        assertTrue(result.contains("10100000"));
    }

    @Test
    public void shouldShowDescriptionWhenBitIsActive() {
        byte[] bytes = new byte[]{(byte) 0b10000000}; // Bit 8 = 1

        String result = BitAnalyzer.analyze(bytes, "9F33").toString();

        assertTrue(result.contains("Bit 8: 1"));
        assertTrue(result.contains("Manual key entry"));
    }

    @Test
    public void shouldAnalyzeMultipleBytesIndependently() {
        byte[] bytes = new byte[]{
                (byte) 0b10000000, // byte 1
                (byte) 0b00000000  // byte 2
        };

        String result = BitAnalyzer.analyze(bytes, "9F33").toString();

        assertTrue(result.contains("Byte 1"));
        assertTrue(result.contains("Byte 2"));
    }

    @Test
    public void shouldHandleAllBitsActive() {
        byte[] bytes = new byte[]{(byte) 0xFF};

        String result = BitAnalyzer.analyze(bytes, "9F33").toString();

        assertTrue(result.contains("11111111"));
    }

    // edge cases

    @Test
    public void shouldReturnMessageWhenTagNotMapped() {
        byte[] bytes = new byte[]{(byte) 0xFF};

        String result = BitAnalyzer.analyze(bytes, "UNKNOWN").toString();

        assertTrue(result.contains("Tag não mapeada"));
    }

    @Test
    public void shouldNotShowDescriptionWhenBitIsZero() {
        byte[] bytes = new byte[]{(byte) 0b00000000};

        String result = BitAnalyzer.analyze(bytes, "9F33").toString();

        assertTrue(result.contains("Bit 8: 0"));
        assertFalse(result.contains("Manual key entry"));
    }

    @Test
    public void shouldNotShowDescriptionWhenBitHasNoDefinition() {
        byte[] bytes = new byte[]{(byte) 0b00000001}; // Bit 1 = 1 (não definido em 9F33 byte 1)

        String result = BitAnalyzer.analyze(bytes, "9F33").toString();

        assertTrue(result.contains("Bit 1: 1"));
        assertFalse(result.contains("Bit 1: 1 →"));
    }

    @Test
    public void shouldHandleMoreBytesThanDefined() { // erro que deve ser tratato no parser, graceful degradation
        byte[] bytes = new byte[]{
                (byte) 0xFF,
                (byte) 0xFF,
                (byte) 0xFF,
                (byte) 0xFF // além dos 3 bytes definidos em 9F33
        };

        String result = BitAnalyzer.analyze(bytes, "9F33").toString();

        assertTrue(result.contains("Byte 4"));
    }
}