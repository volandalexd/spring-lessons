package com.example.todo.client.config;

import com.example.todo.client.error.ToDoErrorHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Alexander Denisov<br/>
 * Date: 26.06.2023<br/>
 * Time: 12:58<br/>
 * Copyright 2023 Connective Games LLC. All rights reserved.<br/>
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate createRestTemplate(ObjectMapper objectMapper) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new ToDoErrorHandler());
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        restTemplate.getMessageConverters().add(0, createMappingJacksonHttpMessageConverter(objectMapper));
        return restTemplate;
    }

    private MappingJackson2HttpMessageConverter createMappingJacksonHttpMessageConverter(ObjectMapper objectMapper) {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper);
        return converter;
    }

}
