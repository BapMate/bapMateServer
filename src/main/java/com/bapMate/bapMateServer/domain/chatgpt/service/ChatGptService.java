package com.bapMate.bapMateServer.domain.chatgpt.service;

import com.bapMate.bapMateServer.domain.chatgpt.config.ChatGptConfig;
import com.bapMate.bapMateServer.domain.chatgpt.dto.request.ChatGptRequestDto;
import com.bapMate.bapMateServer.domain.chatgpt.dto.response.ChatGptResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatGptService {

    private final RestTemplate restTemplate;


    //api key를 application.yml에 넣어두었습니다.
    @Value("${chatgpt.api-key}")
    private String apiKey;

    public ChatGptResponseDto buildHttpEntity(ChatGptRequestDto chatGptRequest){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.parseMediaType(ChatGptConfig.MEDIA_TYPE));
        httpHeaders.add(ChatGptConfig.AUTHORIZATION, ChatGptConfig.BEARER + apiKey);
        return getResponse(new HttpEntity<>(chatGptRequest, httpHeaders));
    }


}
