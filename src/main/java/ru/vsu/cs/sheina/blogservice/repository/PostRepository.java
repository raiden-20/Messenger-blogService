package ru.vsu.cs.sheina.blogservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vsu.cs.sheina.blogservice.entity.PostEntity;

import java.util.List;
import java.util.UUID;

public interface PostRepository extends JpaRepository<PostEntity, Integer> {

    List<PostEntity> findAllByUserId(UUID id);
}
