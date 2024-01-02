package ru.vsu.cs.sheina.blogservice.exception;

import org.springframework.http.HttpStatus;

public class AccessException extends AppException {
    public AccessException() {
        super("Action cannot be performed", HttpStatus.FORBIDDEN);
    }
}
