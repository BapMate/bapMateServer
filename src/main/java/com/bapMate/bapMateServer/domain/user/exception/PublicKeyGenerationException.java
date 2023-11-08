package com.bapMate.bapMateServer.domain.user.exception;

import com.bapMate.bapMateServer.global.exception.GlobalErrorCode;
import com.bapMate.bapMateServer.global.exception.base.BaseException;

public class PublicKeyGenerationException extends BaseException {
    public PublicKeyGenerationException() {
        super(GlobalErrorCode.PUBKEY_GENERATION_FAILED);
    }
}