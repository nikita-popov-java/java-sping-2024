package ru.nikitapopov.skillbox.mod4.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

//@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllExceptions(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}