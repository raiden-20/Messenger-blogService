package ru.vsu.cs.sheina.blogservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vsu.cs.sheina.blogservice.entity.UserDataEntity;

import java.util.UUID;

public interface UserDataRepository extends JpaRepository<UserDataEntity, UUID> {
}
