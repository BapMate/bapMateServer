package com.bapMate.bapMateServer.global.utils;

import com.bapMate.bapMateServer.global.annotation.Processor;
import com.bapMate.bapMateServer.global.exception.base.BaseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Processor
@RequiredArgsConstructor
public class FilterExceptionProcessor {

    private final ObjectMapper objectMapper;
    public void excute(HttpServletResponse response, BaseException e) throws IOException {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("isSuccess", false);
        errorResponse.put("message", e.getMessage());
        errorResponse.put("code", e.getErrorCode());
        errorResponse.put("httpStatus", e.getErrorReason().getStatus());

        response.setCharacterEncoding("UTF-8");
        response.setStatus(e.getErrorReason().getStatus());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}