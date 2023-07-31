package com.example.todo.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

/**
 * Alexander Denisov<br/>
 * Date: 23.06.2023<br/>
 * Time: 15:14<br/>
 * Copyright 2023 Connective Games LLC. All rights reserved.<br/>
 */
public final class ToDoValidationErrorBuilder {

    private ToDoValidationErrorBuilder() {
    }

    public static ToDoValidationError fromBindingErrors(Errors errors) {
        ToDoValidationError error =
                new ToDoValidationError("Validation failed. " + errors.getErrorCount() + " error(s)");
        for (ObjectError objectError : errors.getAllErrors()) {
            error.addValidationError(objectError.getDefaultMessage());
        }
        return error;
    }

}
