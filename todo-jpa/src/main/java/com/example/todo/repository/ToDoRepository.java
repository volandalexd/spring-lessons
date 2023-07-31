package com.example.todo.repository;

import com.example.todo.domain.ToDo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Alexander Denisov<br/>
 * Date: 23.06.2023<br/>
 * Time: 15:02<br/>
 * Copyright 2023 Connective Games LLC. All rights reserved.<br/>
 */
@Repository
public interface ToDoRepository extends CrudRepository<ToDo, String> {
}
