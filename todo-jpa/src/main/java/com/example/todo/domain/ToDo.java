package com.example.todo.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;


import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Alexander Denisov<br/>
 * Date: 23.06.2023<br/>
 * Time: 14:45<br/>
 * Copyright 2023 Connective Games LLC. All rights reserved.<br/>
 */
@Entity
@Data
@NoArgsConstructor
public class ToDo {

    @Id
    @GeneratedValue()
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private String id;

    @NotEmpty
    private String description;

    @Column(insertable = true, updatable = false)
    private LocalDateTime created;

    private LocalDateTime modified;

    private boolean completed;

    public ToDo(String description) {
        this.description = description;
    }

    @PrePersist
    void onCreate() {
        setCreated(LocalDateTime.now());
        setModified(LocalDateTime.now());
    }

    @PreUpdate
    void onUpdate() {
        setModified(LocalDateTime.now());
    }
}
