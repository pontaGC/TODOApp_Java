package com.example.todo_api.controller.advice;

import com.example.todoapi.model.BadRequestError;
import com.example.todoapi.model.InvalidParam;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.stream.Collectors;

class BadRequestErrorCreator {
    public static BadRequestError from(MethodArgumentNotValidException ex){
        var error = new BadRequestError();
        error.setInvalidParams(createInvalidParams(ex));
        return error;
    }

    private static List<InvalidParam> createInvalidParams(MethodArgumentNotValidException ex) {
        return ex.getFieldErrors()
                .stream()
                .map(BadRequestErrorCreator::createOneInvalidParam)
                .collect(Collectors.toList());
    }

    private static InvalidParam createOneInvalidParam(FieldError fieldError){
        var result = new InvalidParam();
        result.setName(fieldError.getField());
        result.setReason(fieldError.getDefaultMessage());
        return  result;
    }
}
