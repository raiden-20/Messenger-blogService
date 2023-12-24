package ru.vsu.cs.sheina.blogservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vsu.cs.sheina.blogservice.entity.PostPhotoEntity;

public interface PostPhotoRepository extends JpaRepository<PostPhotoEntity, Integer> {
}
