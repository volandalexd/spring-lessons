package com.example.todo.client;

import com.example.todo.client.config.ToDoRestClientProperties;
import com.example.todo.client.domain.ToDo;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Objects;

/**
 * Alexander Denisov<br/>
 * Date: 23.06.2023<br/>
 * Time: 16:41<br/>
 * Copyright 2023 Connective Games LLC. All rights reserved.<br/>
 */
@Service
public class ToDoRestClient {
    private final RestTemplate restTemplate;
    private final ToDoRestClientProperties properties;

    public ToDoRestClient(RestTemplate restTemplate, ToDoRestClientProperties properties) {
        this.restTemplate = restTemplate;
        this.properties = properties;
    }

    public Iterable<ToDo> findAll()
            throws URISyntaxException {
        RequestEntity<Iterable<ToDo>> requestEntity = new RequestEntity<>(HttpMethod.GET, new URI(getUri()));
        ResponseEntity<Iterable<ToDo>> response = restTemplate.exchange(requestEntity,
                new ParameterizedTypeReference<>() {});
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }
        return null;
    }

    private String getUri() {
        return properties.getUrl() + properties.getBasePath();
    }

    public ToDo findById(String id) {
        return restTemplate.getForObject(getUri() + "/{id}", ToDo.class, Map.of("id", id));
    }

    public ToDo upsert(ToDo toDo)
            throws URISyntaxException {
        RequestEntity<ToDo> requestEntity = new RequestEntity<>(toDo, HttpMethod.POST, new URI(getUri()));
        ResponseEntity<ToDo> response = restTemplate.exchange(requestEntity, new ParameterizedTypeReference<>() {
        });
        if (response.getStatusCode() == HttpStatus.CREATED) {
            return restTemplate.getForObject(Objects.requireNonNull(response.getHeaders().getLocation()), ToDo.class);
        }
        return null;
    }

    public ToDo setCompleted(String id) {
        restTemplate.patchForObject(getUri() + "/{id}",
                null,
                ResponseEntity.class,
                Map.of("id", id));
        return findById(id);
    }

    public void delete(String id) {
        restTemplate.delete(getUri() + "/{id}", Map.of("id", id));
    }
}
