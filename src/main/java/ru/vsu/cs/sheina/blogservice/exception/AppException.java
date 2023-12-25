package ru.vsu.cs.sheina.blogservice.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class AppException extends RuntimeException{
    private final HttpStatus status;

    public AppException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
