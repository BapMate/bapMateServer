package com.bapMate.bapMateServer.domain.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {

    GUEST("GUEST"),
    USER("USER"),
    ADMIN("ADMIN");

    private String value;
}