package ru.vsu.cs.sheina.blogservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vsu.cs.sheina.blogservice.entity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
}
