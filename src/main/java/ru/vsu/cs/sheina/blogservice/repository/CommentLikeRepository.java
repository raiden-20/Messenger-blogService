package ru.vsu.cs.sheina.blogservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vsu.cs.sheina.blogservice.entity.CommentLikeEntity;

public interface CommentLikeRepository extends JpaRepository<CommentLikeEntity, Integer> {
}
