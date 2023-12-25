package ru.vsu.cs.sheina.blogservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PictureDTO {

    Integer postId;

    Integer photoId;

    String url;
}
