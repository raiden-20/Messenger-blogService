package ru.vsu.cs.sheina.blogservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vsu.cs.sheina.blogservice.entity.PostLikeEntity;

public interface PostLikeRepository extends JpaRepository<PostLikeEntity, Integer> {
}
