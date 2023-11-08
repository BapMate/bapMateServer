package com.bapMate.bapMateServer.global.exception;

import com.bapMate.bapMateServer.global.exception.base.BaseException;

public class InvalidSignatureTokenException extends BaseException {
    public InvalidSignatureTokenException(){
        super(GlobalErrorCode.INVALID_SIGNATURE_TOKEN);
    }
}
