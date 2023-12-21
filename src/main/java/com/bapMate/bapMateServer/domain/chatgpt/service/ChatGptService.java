package com.bapMate.bapMateServer.domain.chatgpt.service;

import com.bapMate.bapMateServer.domain.chatgpt.config.ChatGptConfig;
import com.bapMate.bapMateServer.domain.chatgpt.dto.request.ChatGptRequestDto;
import com.bapMate.bapMateServer.domain.chatgpt.dto.response.ChatGptResponseDto;
import com.bapMate.bapMateServer.domain.chatgpt.entity.ChatGptMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

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
    public ChatGptResponseDto getResponse(HttpEntity<ChatGptRequestDto> chatGptRequestHttpEntity){

        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(60000);
        //답변이 길어질 경우 TimeOut Error가 발생하니 1분정도 설정해줍니다.
        requestFactory.setReadTimeout(60 * 1000);   //  1min = 60 sec * 1,000ms
        restTemplate.setRequestFactory(requestFactory);

        ResponseEntity<ChatGptResponseDto> responseEntity = restTemplate.postForEntity(
                ChatGptConfig.CHAT_URL,
                chatGptRequestHttpEntity,
                ChatGptResponseDto.class);

        return responseEntity.getBody();
    }

    public ResponseEntity askQuestion(String questionRequest){
        List<ChatGptMessage> messages = new ArrayList<>();
        messages.add(ChatGptMessage.builder()
                .role(ChatGptConfig.ROLE)
                .content(questionRequest)
                .build());
        ChatGptResponseDto test = buildHttpEntity( new ChatGptRequestDto(
                ChatGptConfig.CHAT_MODEL,
                ChatGptConfig.MAX_TOKEN,
                ChatGptConfig.TEMPERATURE,
                ChatGptConfig.STREAM,
                messages
                //ChatGptConfig.TOP_P
        ));
        //우리가 만들 response dto chatGptColorResponseDTO = .builder()
        //         .color(test.getChoices().get(0).getMessage().getContent())
         //       .build();
        //return chatGptColorResponseDTO;
    }




}
