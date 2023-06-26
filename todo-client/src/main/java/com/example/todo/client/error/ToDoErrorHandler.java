package com.example.todo.client.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Alexander Denisov<br/>
 * Date: 23.06.2023<br/>
 * Time: 16:29<br/>
 * Copyright 2023 Connective Games LLC. All rights reserved.<br/>
 */
public class ToDoErrorHandler extends DefaultResponseErrorHandler {
    private final Logger logger = LoggerFactory.getLogger(ToDoErrorHandler.class);

    @Override
    public void handleError(ClientHttpResponse response)
            throws IOException {
        logger.error(response.getStatusCode().toString());
        logger.error(StreamUtils.copyToString(response.getBody(), StandardCharsets.UTF_8));
    }
}
