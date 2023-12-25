package ru.vsu.cs.sheina.blogservice.exception;

import org.springframework.http.HttpStatus;

public class PostDoesntExistException extends AppException{
    public PostDoesntExistException() {
        super("Post doesn't exist", HttpStatus.BAD_REQUEST);
    }
}
