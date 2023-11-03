package com.bapMate.bapMateServer.global.jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class DecodedJwtToken {
    private Long userId;
    private String role;
    private String type;
}