package com.challange.marvel.exception;

import org.springframework.http.HttpStatus;
import lombok.Getter;

@Getter
public class EntityException extends RuntimeException {

    private final HttpStatus status;

    public EntityException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public EntityException(String message, Throwable cause, HttpStatus status) {
        super(message, cause);
        this.status = status;
    }

    public EntityException(Throwable cause, HttpStatus status) {
        super(cause);
        this.status = status;
    }
}
