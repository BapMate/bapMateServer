package com.bapMate.bapMateServer.global.exception;

import com.bapMate.bapMateServer.global.exception.base.BaseException;

public class InvalidTokenException extends BaseException {
    public InvalidTokenException(){
        super(GlobalErrorCode.INVALID_TOKEN);
    }
}
