package com.example.todo.repository;

import com.example.todo.domain.ToDo;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Alexander Denisov<br/>
 * Date: 23.06.2023<br/>
 * Time: 15:02<br/>
 * Copyright 2023 Connective Games LLC. All rights reserved.<br/>
 */
@Repository
public class ToDoRepository implements CommonRepository<ToDo> {
    private final Map<String, ToDo> toDos = new HashMap<>();

    @Override
    public ToDo save(ToDo domain) {
        ToDo toDo = toDos.get(domain.getId());
        if (toDo != null) {
            toDo.setModified(domain.getModified());
            toDo.setDescription(domain.getDescription());
            toDo.setCompleted(domain.isCompleted());
            domain = toDo;
        }
        toDos.put(domain.getId(), domain);
        return domain;
    }

    @Override
    public Iterable<ToDo> save(Collection<ToDo> domains) {
        domains.forEach(this::save);
        return findAll();
    }

    @Override
    public void delete(ToDo domain) {
        toDos.remove(domain.getId());
    }

    @Override
    public ToDo findById(String id) {
        return toDos.get(id);
    }

    @Override
    public Iterable<ToDo> findAll() {
        return toDos.values().stream().sorted(
                Comparator.comparing(ToDo::getCreated)
        ).collect(Collectors.toList());
    }
}
