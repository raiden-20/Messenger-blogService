package ru.vsu.cs.sheina.blogservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.vsu.cs.sheina.blogservice.entity.PostPhotoEntity;

import java.util.List;
import java.util.Optional;

public interface PostPhotoRepository extends JpaRepository<PostPhotoEntity, Integer> {

    List<PostPhotoEntity> findAllByPostId(Integer id);

    boolean existsByPostId(Integer postId);

    @Transactional
    void deleteAllByPostId(Integer postId);
}
