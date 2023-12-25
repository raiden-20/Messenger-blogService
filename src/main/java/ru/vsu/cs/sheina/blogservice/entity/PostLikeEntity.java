package ru.vsu.cs.sheina.blogservice.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "post_like")
public class PostLikeEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "user_id")
    UUID userId;

    @Column(name = "post_id")
    Integer postId;
}
