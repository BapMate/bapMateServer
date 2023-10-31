package com.bapMate.bapMateServer.global.exception.base;

import com.bapMate.bapMateServer.global.exception.base.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseException extends RuntimeException {
    private final BaseErrorCode errorCode;
}
