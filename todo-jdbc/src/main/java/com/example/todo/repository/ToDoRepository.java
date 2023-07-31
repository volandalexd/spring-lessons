package com.example.todo.repository;

import com.example.todo.domain.ToDo;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Map;

/**
 * Alexander Denisov<br/>
 * Date: 23.06.2023<br/>
 * Time: 15:02<br/>
 * Copyright 2023 Connective Games LLC. All rights reserved.<br/>
 */
@Repository
public class ToDoRepository implements CommonRepository<ToDo> {

    private static final String SQL_INSERT = "insert into todo " +
                                             "(id, description, created, modified, completed)" +
                                             "values (:id, :description, :created, :modified, :completed";
    private static final String SQL_UPDATE = "update todo set " +
                                             "description = :description, modified = :modified, completed = :completed " +
                                             "where id = :id";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public ToDoRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<ToDo> toDoRowMapper = (ResultSet rs, int rowNum) -> {
        ToDo toDo = new ToDo();
        toDo.setId(rs.getString("id"));
        toDo.setDescription(rs.getString("description"));
        toDo.setModified(rs.getTimestamp("modified").toLocalDateTime());
        toDo.setCreated(rs.getTimestamp("created").toLocalDateTime());
        toDo.setCompleted(rs.getBoolean("completed"));
        return toDo;
    };

    @Override
    public ToDo save(ToDo domain) {
        ToDo toDo = findById(domain.getId());
        if (toDo != null) {
            toDo.setModified(domain.getModified());
            toDo.setDescription(domain.getDescription());
            toDo.setCompleted(domain.isCompleted());
            return upsert(toDo, SQL_UPDATE);
        }
        return upsert(domain, SQL_INSERT);
    }

    private ToDo upsert(ToDo domain, String sql) {
        Map<String, Object> parameters = Map.of(
                "id", domain.getId(),
                "description", domain.getDescription(),
                "created", Timestamp.valueOf(domain.getCreated()),
                "modified", Timestamp.valueOf(domain.getModified()),
                "completed", domain.isCompleted());
        jdbcTemplate.update(sql, parameters);
        return findById(domain.getId());
    }


    @Override
    public Iterable<ToDo> save(Collection<ToDo> domains) {
        domains.forEach(this::save);
        return findAll();
    }

    @Override
    public void delete(ToDo domain) {
        jdbcTemplate.update("delete from todo where id = :id", Map.of("id", domain.getId()));
    }

    @Override
    public ToDo findById(String id) {
        try {
            return jdbcTemplate.queryForObject(
                    "select * from todo where id = :id",
                    Map.of("id", id),
                    toDoRowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Iterable<ToDo> findAll() {
        return jdbcTemplate.query("select * from todo", toDoRowMapper);
    }
}
