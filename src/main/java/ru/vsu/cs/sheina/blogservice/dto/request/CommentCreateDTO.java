package ru.vsu.cs.sheina.blogservice.dto.request;

import lombok.Data;

@Data
public class CommentCreateDTO {

    Integer postId;

    String text;
}
