package ru.vsu.cs.sheina.blogservice.exception;

import org.springframework.http.HttpStatus;

public class TextTooBigException extends AppException {
    public TextTooBigException() {
        super("Too many characters", HttpStatus.BAD_REQUEST);
    }
}
