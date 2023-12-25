package ru.vsu.cs.sheina.blogservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.vsu.cs.sheina.blogservice.entity.PostLikeEntity;

import java.util.Optional;
import java.util.UUID;

public interface PostLikeRepository extends JpaRepository<PostLikeEntity, Integer> {

    Integer countByPostId(Integer postId);

    boolean existsByPostId(Integer postId);

    @Transactional
    void deleteAllByPostId(Integer postId);

    Boolean existsByPostIdAndUserId(Integer postId, UUID userId);

    Optional<PostLikeEntity> findByPostIdAndUserId(Integer postId, UUID userId);
}
