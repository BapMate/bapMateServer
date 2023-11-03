package com.bapMate.bapMateServer.global.exception;

import com.bapMate.bapMateServer.global.exception.base.BaseException;

public class NotMatchedTokenTypeException extends BaseException {

    public NotMatchedTokenTypeException() {
        super(GlobalErrorCode.NOT_MATCHED_TOKEN_TYPE);
    }
}