package com.challange.marvel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.arnaudpiroelle.marvel.api.exceptions.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ExceptionTranslator {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> processEntityNotFound(EntityNotFoundException ex) {
        log.error("Resource not found on Marvel Characters API.", ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getCause().getMessage());
    }
}
