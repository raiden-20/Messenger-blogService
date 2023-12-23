package ru.vsu.cs.sheina.blogservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Data
@Table(name = "comment")
public class CommentEntity {
    @Id
    @Column(name = "id")
    Integer id;

    @Column(name = "user_id")
    UUID userId;

    @Column(name = "post_id")
    Integer postId;

    @Column(name = "body")
    String body;

    @Column(name = "created_at")
    Timestamp createdAt;
}
