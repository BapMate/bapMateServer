package com.bapMate.bapMateServer.domain.user.exception;

import com.bapMate.bapMateServer.global.exception.base.BaseException;

public class ExpiredRefreshTokenException extends BaseException {
    public ExpiredRefreshTokenException() {
        super(RefreshTokenErrorCode.EXPIRED_REFRESH_TOKEN);
    }
}
