package com.study.message;

public class JoinMessage implements Message {
    private final String type = "join";
    private String name;
    public JoinMessage() {
    }

    public String getType() {
        return type;
    }

    public JoinMessage(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
