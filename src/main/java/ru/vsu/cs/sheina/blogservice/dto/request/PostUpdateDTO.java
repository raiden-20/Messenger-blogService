package ru.vsu.cs.sheina.blogservice.dto.request;

import lombok.Data;

@Data
public class PostUpdateDTO {

    Integer postId;

    String text;
}
