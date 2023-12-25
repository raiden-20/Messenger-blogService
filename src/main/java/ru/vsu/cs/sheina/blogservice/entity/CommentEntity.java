package ru.vsu.cs.sheina.blogservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Data
@Table(name = "comment")
public class CommentEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "user_id")
    UUID userId;

    @Column(name = "post_id")
    Integer postId;

    @Column(name = "body")
    String body;

    @Column(name = "created_at")
    Timestamp createdAt;

    @Column(name = "changed")
    Boolean changed;
}
