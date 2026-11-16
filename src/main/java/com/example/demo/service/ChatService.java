package com.example.demo.service;

import com.example.demo.client.AiClient;
import com.example.demo.dto.ChatRequest;
import com.example.demo.dto.ChatResponse;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final AiClient aiClient;

    public ChatService(AiClient aiClient) {
        this.aiClient = aiClient;
    }

    public ChatResponse chat(ChatRequest request) {
        return aiClient.chat(request);
    }
}