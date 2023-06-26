package com.example.todo.domain;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * Alexander Denisov<br/>
 * Date: 23.06.2023<br/>
 * Time: 14:52<br/>
 * Copyright 2023 Connective Games LLC. All rights reserved.<br/>
 */
public final class ToDoBuilder {
    private @NotNull String id;
    private @NotEmpty String description;

    private ToDoBuilder() {
    }

    public static ToDoBuilder create() {
        return new ToDoBuilder();
    }

    public ToDoBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public ToDoBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public ToDo build() {
        ToDo toDo = new ToDo(description);
        if (id != null) {
            toDo.setId(id);
        }
        return toDo;
    }
}
