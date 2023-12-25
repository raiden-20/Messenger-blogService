package ru.vsu.cs.sheina.blogservice.dto.rabbitmq;

public enum FileSource {
    AVATAR("AVATAR"),
    COVER("COVER"),
    POST("POST");

    private String name;

    FileSource(String name) {
        this.name = name;
    }
}
