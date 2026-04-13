package com.example.testepositivo.model;

import java.util.List;

public class TagDefinition {

    private String tag;

    private List<ByteDefinition> bytes;

    public TagDefinition(String tag, List<ByteDefinition> bytes) {
        this.tag = tag;
        this.bytes = bytes;
    }

    public String getTag() {
        return tag;
    }

    public List<ByteDefinition> getBytes() {
        return bytes;
    }
}
