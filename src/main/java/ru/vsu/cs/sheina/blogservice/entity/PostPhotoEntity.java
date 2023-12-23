package ru.vsu.cs.sheina.blogservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "post_photo")
public class PostPhotoEntity {
    @Id
    @Column(name = "id")
    Integer id;

    @Column(name = "post_id")
    Integer postId;

    @Column(name = "photo_url")
    String photoUrl;
}
