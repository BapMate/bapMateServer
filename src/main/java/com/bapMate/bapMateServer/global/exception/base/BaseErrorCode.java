package com.bapMate.bapMateServer.global.exception.base;

import com.bapMate.bapMateServer.global.exception.dto.ErrorReason;

public interface BaseErrorCode {
    public ErrorReason getErrorReason();
}
