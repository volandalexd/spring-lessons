package com.example.todo.repository;

import java.util.Collection;

/**
 * Alexander Denisov<br/>
 * Date: 23.06.2023<br/>
 * Time: 15:00<br/>
 * Copyright 2023 Connective Games LLC. All rights reserved.<br/>
 */
public interface CommonRepository<T> {
    T save(T domain);

    Iterable<T> save(Collection<T> domains);

    void delete(T domain);

    T findById(String id);

    Iterable<T> findAll();
}
