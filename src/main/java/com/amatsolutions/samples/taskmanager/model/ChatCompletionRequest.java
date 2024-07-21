package com.amatsolutions.samples.taskmanager.model;

import lombok.Data;

import java.util.List;

@Data
public class ChatCompletionRequest {
    private String model;
    private List<ChatMessage> messages;

    public ChatCompletionRequest() {}

    public ChatCompletionRequest(String model, List<ChatMessage> messages) {
        this.model = model;
        this.messages = messages;
    }

    // Getters and setters
    // ...
}
