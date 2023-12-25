package ru.vsu.cs.sheina.blogservice.dto.rabbitmq;

import lombok.Data;

@Data
public class UrlDTO {
    String sourceId;

    String url;

    Integer photoId;
}
