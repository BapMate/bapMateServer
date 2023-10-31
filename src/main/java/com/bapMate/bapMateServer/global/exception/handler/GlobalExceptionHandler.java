package com.bapMate.bapMateServer.global.exception.handler;

import com.bapMate.bapMateServer.global.exception.GlobalErrorCode;
import com.bapMate.bapMateServer.global.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException Error", e);
        ErrorResponse error = com.bapMate.bapMateServer.global.response.ErrorResponse.builder()
                                .errorCode(GlobalErrorCode.INVALID_HTTP_MESSAGE_BODY)
                                .build();
        return ResponseEntity.status(error.getHttpStatus()).body(error);
    }
}
