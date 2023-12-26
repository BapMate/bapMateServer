package com.bapMate.bapMateServer.domain.chatgpt.entity;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ChatGptMessage {
    private String role;
    private String content;
}
