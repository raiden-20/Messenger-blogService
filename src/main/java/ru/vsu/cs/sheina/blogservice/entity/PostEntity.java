package ru.vsu.cs.sheina.blogservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Data
@Table(name = "post")
public class PostEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "user_id")
    UUID userId;

    @Column(name = "body")
    String body;

    @Column(name = "created_at")
    Timestamp createdAt;
}
