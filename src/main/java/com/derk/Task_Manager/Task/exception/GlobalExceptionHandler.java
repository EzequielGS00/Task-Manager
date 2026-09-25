package com.derk.Task_Manager.Task.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {
    @ExceptionHandler(
            {TaskNotFoundException.class,
                    StatusNotFoundException.class,
                    PriorityNotFoundException.class
            })
    public ResponseEntity<String> handleTaskNotFound(
            RuntimeException exception
    ){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }
}
