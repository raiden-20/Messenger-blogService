package ru.vsu.cs.sheina.blogservice.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "post_like")
public class PostLikeEntity {
    @Id
    @Column(name = "id")
    Integer id;

    @Column(name = "user_id")
    UUID userId;

    @Column(name = "post_id")
    Integer postId;
}
