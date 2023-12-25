package ru.vsu.cs.sheina.blogservice.dto.request;

import lombok.Data;

@Data
public class CommentUpdateDTO {

    Integer commentId;

    String text;
}
