package com.bapMate.bapMateServer.domain.user.entity;

import com.bapMate.bapMateServer.domain.user.exception.NotSupportedLoginTypeException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LoginType {
    KAKAO("kakao");

    private final String value;

    public static LoginType fromValue(String value) {
        for (LoginType type : LoginType.values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new NotSupportedLoginTypeException();
    }
}