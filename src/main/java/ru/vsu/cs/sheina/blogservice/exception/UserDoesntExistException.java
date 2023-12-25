package ru.vsu.cs.sheina.blogservice.exception;

import org.springframework.http.HttpStatus;

public class UserDoesntExistException extends AppException{
    public UserDoesntExistException() {
        super("User doesn't exist", HttpStatus.BAD_REQUEST);
    }
}
