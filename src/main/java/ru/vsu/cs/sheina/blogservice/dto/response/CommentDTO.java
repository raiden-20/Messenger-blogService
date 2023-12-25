package ru.vsu.cs.sheina.blogservice.dto.response;

import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class CommentDTO {

    Integer commentId;

    UUID userId;

    String text;

    Timestamp time;

    Integer likeCount;

    Boolean isLiked;
}
