package ru.vsu.cs.sheina.blogservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "user_data")
public class UserDataEntity {
    @Id
    @Column(name = "id")
    UUID id;
}
