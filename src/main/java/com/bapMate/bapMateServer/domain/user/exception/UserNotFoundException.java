package com.bapMate.bapMateServer.domain.user.exception;

import com.bapMate.bapMateServer.global.exception.base.BaseException;

public class UserNotFoundException extends BaseException {
    public UserNotFoundException() {
        super(UserErrorCode.USER_NOT_FOUND);
    }
}
