package ru.vsu.cs.sheina.blogservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "post_photo")
public class PostPhotoEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "post_id")
    Integer postId;

    @Column(name = "photo_url")
    String photoUrl;
}
