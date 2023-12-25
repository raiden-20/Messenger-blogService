package ru.vsu.cs.sheina.blogservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "comment_like")
public class CommentLikeEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "user_id")
    UUID userId;

    @Column(name = "comment_id")
    Integer commentId;
}
