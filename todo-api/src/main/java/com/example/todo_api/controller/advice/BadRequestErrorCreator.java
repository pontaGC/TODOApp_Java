package com.example.todo_api.controller.advice;

import com.example.todoapi.model.BadRequestError;
import com.example.todoapi.model.InvalidParam;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ElementKind;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

class BadRequestErrorCreator {

    //region Public Methods
    public static BadRequestError from(MethodArgumentNotValidException ex){
        var error = new BadRequestError();
        error.setInvalidParams(createInvalidParams(ex));
        return error;
    }

    public static BadRequestError from(ConstraintViolationException ex) {
        var error = new BadRequestError();
        error.setInvalidParams(createInvalidParams(ex));
        return error;
    }

    //endregion

    //region Private Methods

    private static List<InvalidParam> createInvalidParams(ConstraintViolationException ex) {
        return ex.getConstraintViolations()
                .stream()
                .map(BadRequestErrorCreator::createOneInvalidParam)
                .collect(Collectors.toList());
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
        return result;
    }

    private static InvalidParam createOneInvalidParam(ConstraintViolation<?> v) {
        var parameterNode = StreamSupport.stream(v.getPropertyPath().spliterator(), false)
                .filter(node -> node.getKind().equals(ElementKind.PARAMETER))
                .findFirst();
        var invalidParam = new InvalidParam();
        parameterNode.ifPresent(p -> invalidParam.setName(p.getName()));
        invalidParam.setReason(v.getMessage());
        return invalidParam;
    }

    //endregion
}
