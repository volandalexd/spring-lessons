package com.example.todo.validation;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

/**
 * Alexander Denisov<br/>
 * Date: 23.06.2023<br/>
 * Time: 15:10<br/>
 * Copyright 2023 Connective Games LLC. All rights reserved.<br/>
 */
public class ToDoValidationError {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final List<String> errors = new ArrayList<>();

    private final String errorMessage;

    public ToDoValidationError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void addValidationError(String msg) {
        errors.add(msg);
    }

    public List<String> getErrors() {
        return errors;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
