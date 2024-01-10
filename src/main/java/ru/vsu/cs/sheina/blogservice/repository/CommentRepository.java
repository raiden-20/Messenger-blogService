package ru.vsu.cs.sheina.blogservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vsu.cs.sheina.blogservice.entity.CommentEntity;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {

    Integer countByPostId(Integer postId);

    List<CommentEntity> findAllByPostId(Integer postId);

    Boolean existsByPostId(Integer postId);
}
