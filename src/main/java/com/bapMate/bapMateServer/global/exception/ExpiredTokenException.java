package com.bapMate.bapMateServer.global.exception;

import com.bapMate.bapMateServer.global.exception.base.BaseException;

public class ExpiredTokenException extends BaseException {
    public ExpiredTokenException(){
        super(GlobalErrorCode.EXPIRED_TOKEN);
    }
}