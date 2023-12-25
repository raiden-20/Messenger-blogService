package ru.vsu.cs.sheina.blogservice.exception;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends AppException{
    public UserAlreadyExistsException() {
        super("User already exist", HttpStatus.CONFLICT);
    }
}
