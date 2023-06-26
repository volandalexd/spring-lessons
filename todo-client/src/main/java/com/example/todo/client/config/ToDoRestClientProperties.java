package com.example.todo.client.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Alexander Denisov<br/>
 * Date: 23.06.2023<br/>
 * Time: 16:32<br/>
 * Copyright 2023 Connective Games LLC. All rights reserved.<br/>
 */
@Component
@ConfigurationProperties(prefix = "todo")
@Data
public class ToDoRestClientProperties {
    private String url;
    private String basePath;
}
