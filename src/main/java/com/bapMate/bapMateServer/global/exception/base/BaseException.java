package com.bapMate.bapMateServer.global.exception.base;

import com.bapMate.bapMateServer.global.exception.base.BaseErrorCode;
import com.bapMate.bapMateServer.global.exception.dto.ErrorReason;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseException extends RuntimeException {
    private final BaseErrorCode errorCode;

    public ErrorReason getErrorReason() {
        return this.errorCode.getErrorReason();
    }
}

