package ru.vsu.cs.sheina.blogservice.dto.rabbitmq;

import lombok.Data;

@Data
public class UrlDTO {
    String url;

    Integer photoId;

    Integer postId;
}
