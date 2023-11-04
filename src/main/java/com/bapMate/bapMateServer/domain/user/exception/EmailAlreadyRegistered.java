package com.bapMate.bapMateServer.domain.user.exception;

import com.bapMate.bapMateServer.global.exception.base.BaseException;

public class EmailAlreadyRegistered extends BaseException {
    public EmailAlreadyRegistered() {
        super(UserErrorCode.EMAIL_ALREADY_REGISTERED);
    }
}