package com.bapMate.bapMateServer.domain.chatgpt.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ChatGptMessage {
    private String role;
    private String content;

    @Builder
    public ChatGptMessage(String role, String content) {
        this.role = role;
        this.content = content;
    }
}
