package com.bapMate.bapMateServer.global.response;

import com.bapMate.bapMateServer.global.exception.base.BaseErrorCode;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ErrorResponse extends BaseResponse {
    private final int httpStatus;

    @Builder
    public ErrorResponse(BaseErrorCode errorCode) {
        super(false, errorCode.getCode(), errorCode.getMessage());
        this.httpStatus = errorCode.getHttpStatus();
    }
}