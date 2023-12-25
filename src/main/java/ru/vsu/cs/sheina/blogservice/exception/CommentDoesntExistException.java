package ru.vsu.cs.sheina.blogservice.exception;

import org.springframework.http.HttpStatus;

public class CommentDoesntExistException extends AppException {
    public CommentDoesntExistException() {
        super("Comment doesn't exist", HttpStatus.BAD_REQUEST);
    }
}
