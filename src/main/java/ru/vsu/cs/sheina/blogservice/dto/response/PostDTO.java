package ru.vsu.cs.sheina.blogservice.dto.response;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class PostDTO {

    Integer postId;

    String text;

    List<PictureDTO> photoUrl;

    Timestamp time;

    Integer likeCount;

    Integer commentCount;

    Boolean isLiked;
}
