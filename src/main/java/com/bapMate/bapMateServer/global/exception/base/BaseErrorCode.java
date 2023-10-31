package com.bapMate.bapMateServer.global.exception.base;

public interface BaseErrorCode {
    public String getCode();
    public String getMessage();
    public int getHttpStatus();
}
