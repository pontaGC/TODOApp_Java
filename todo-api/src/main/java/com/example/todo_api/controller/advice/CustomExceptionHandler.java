package com.example.todo_api.controller.advice;

import com.example.todo_api.service.task.TaskEntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(TaskEntityNotFoundException.class)
    public ResponseEntity<Void> handleTaskEntityNotFoundException(TaskEntityNotFoundException exception){
        return ResponseEntity.notFound().build();
    }
}
