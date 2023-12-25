package ru.vsu.cs.sheina.blogservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.vsu.cs.sheina.blogservice.entity.CommentLikeEntity;

import java.util.Optional;
import java.util.UUID;

public interface CommentLikeRepository extends JpaRepository<CommentLikeEntity, Integer> {

    Integer countByCommentId(Integer id);

    boolean existsByCommentId(Integer commentId);

    @Transactional
    void deleteAllByCommentId(Integer commentId);

    Boolean existsByCommentIdAndUserId(Integer commentId, UUID userId);
    Optional<CommentLikeEntity> findByCommentIdAndUserId(Integer commentId, UUID userId);
}
