package com.example.todo.client.config;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;

/**
 * Alexander Denisov<br/>
 * Date: 26.06.2023<br/>
 * Time: 10:09<br/>
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

//    @Bean
//    @Primary
//    public ObjectMapper objectMapper() {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateTimeFormat);
//        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
//
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimeFormat);
//        SimpleModule module = new SimpleModule();
//        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(dateTimeFormatter));
//        module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(dateTimeFormatter));
//
//        return JsonMapper.builder()
//                .serializationInclusion(JsonInclude.Include.NON_NULL)
//                //.defaultDateFormat(StdDateFormat.instance.withColonInTimeZone(true))
//                .defaultDateFormat(simpleDateFormat)
//                .enable(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN)
//                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
//                //.addModule(new JavaTimeModule())
//                .addModule(module)
//                .build();
//    }
}
