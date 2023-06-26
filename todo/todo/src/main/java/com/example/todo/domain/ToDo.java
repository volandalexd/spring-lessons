package com.example.todo.domain;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Alexander Denisov<br/>
 * Date: 23.06.2023<br/>
 * Time: 14:45<br/>
 * Copyright 2023 Connective Games LLC. All rights reserved.<br/>
 */
@Data
public class ToDo {
    @NotNull
    private String id;
    @NotEmpty
    private String description;
    private LocalDateTime created;
    private LocalDateTime modified;
    private boolean completed;

    public ToDo() {
        LocalDateTime now = LocalDateTime.now();
        this.id = UUID.randomUUID().toString();
        this.created = now;
        this.modified = now;
    }

    public ToDo(String description) {
        this();
        this.description = description;
    }
}
