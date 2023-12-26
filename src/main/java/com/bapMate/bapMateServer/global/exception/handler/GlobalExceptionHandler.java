package com.bapMate.bapMateServer.global.exception.handler;

import com.bapMate.bapMateServer.global.exception.GlobalErrorCode;
import com.bapMate.bapMateServer.global.exception.base.BaseException;
import com.bapMate.bapMateServer.global.exception.dto.ErrorReason;
import com.bapMate.bapMateServer.global.response.ErrorResponse;
import io.micrometer.common.lang.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex,
            @Nullable Object body,
            HttpHeaders headers,
            HttpStatus statusCode,
            WebRequest request) {
        log.error("HandleInternalException", ex);
        final HttpStatus status = (HttpStatus) statusCode;
        final ErrorReason errorReason =
                ErrorReason.of(status.value(), status.name(), ex.getMessage());
        final ErrorResponse errorResponse = ErrorResponse.from(errorReason);
        return super.handleExceptionInternal(ex, errorResponse, headers, status, request);
    }

    // 비즈니스 로직 에러 처리
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleBaseErrorException(
            BaseException e, HttpServletRequest request) {
        log.error("BaseErrorException", e);
        final ErrorReason errorReason = e.getErrorCode().getErrorReason();
        final ErrorResponse errorResponse = ErrorResponse.from(errorReason);
        return ResponseEntity.status(HttpStatus.valueOf(errorReason.getStatus()))
                .body(errorResponse);
    }
}
