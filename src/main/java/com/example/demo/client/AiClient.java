package com.example.demo.client;

import com.example.demo.dto.ChatRequest;
import com.example.demo.dto.ChatResponse;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class AiClient {

    private final RestClient restClient;

    public AiClient(@Qualifier("aiRestClient") RestClient restClient) {
        this.restClient = restClient;
    }

    public ChatResponse chat(ChatRequest request) {

        return restClient
                .post()
                .uri("/chat")
                .body(request)
                .retrieve()
                .body(ChatResponse.class);
    }
}
