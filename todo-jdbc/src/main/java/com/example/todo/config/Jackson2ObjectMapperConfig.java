package com.example.todo.config;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;

/**
 * Alexander Denisov<br/>
 * Date: 23.06.2023<br/>
 * Time: 19:16<br/>
 * Copyright 2023 Connective Games LLC. All rights reserved.<br/>
 */
@Configuration
public class Jackson2ObjectMapperConfig {

    @Value("${spring.jackson.date-format}")
    private String dateTimeFormat;

    @Value("${spring.jackson.time-zone}")
    private String timeZone;

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        return builder -> {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimeFormat);
            builder.serializers(new LocalDateTimeSerializer(dateTimeFormatter));
            builder.deserializers(new LocalDateTimeDeserializer(dateTimeFormatter));
            builder.timeZone(timeZone);
        };
    }
}
