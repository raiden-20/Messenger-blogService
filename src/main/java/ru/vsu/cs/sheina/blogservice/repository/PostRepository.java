package ru.vsu.cs.sheina.blogservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vsu.cs.sheina.blogservice.entity.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, Integer> {
}
