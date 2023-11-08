package com.bapMate.bapMateServer.domain.user.exception;

import com.bapMate.bapMateServer.global.exception.base.BaseException;

public class NotSupportedLoginTypeException extends BaseException {
    public NotSupportedLoginTypeException() {
        super(UserErrorCode.NOT_SUPPORTED_LOGIN_TYPE);
    }
}