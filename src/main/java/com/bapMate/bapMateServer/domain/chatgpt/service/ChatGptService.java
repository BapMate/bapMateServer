package com.bapMate.bapMateServer.domain.chatgpt.service;

import com.bapMate.bapMateServer.domain.chatgpt.config.ChatGptConfig;
import com.bapMate.bapMateServer.domain.chatgpt.dto.request.ChatGptRequestDto;
import com.bapMate.bapMateServer.domain.chatgpt.dto.response.ChatGptResponseDto;
import com.bapMate.bapMateServer.domain.chatgpt.entity.ChatGptMessage;
import com.bapMate.bapMateServer.domain.keyword.entity.Hobby;
import com.bapMate.bapMateServer.domain.keyword.entity.Personality;
import com.bapMate.bapMateServer.domain.keyword.repository.HobbyRepository;
import com.bapMate.bapMateServer.domain.keyword.repository.PersonalityRepository;
import com.bapMate.bapMateServer.domain.user.entity.User;
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

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatGptService {

    private final RestTemplate restTemplate;
    private final HobbyRepository hobbyRepository;
    private final PersonalityRepository personalityRepository;


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


    public String askQuestion(User user){
        List<ChatGptMessage> messages = new ArrayList<>();
        messages.add(ChatGptMessage.builder()
                .role(ChatGptConfig.ROLE)
                .content(getQuestion(user))
                .build());

        ChatGptResponseDto test = buildHttpEntity( new ChatGptRequestDto(
                ChatGptConfig.CHAT_MODEL,
                ChatGptConfig.MAX_TOKEN,
                ChatGptConfig.TEMPERATURE,
                ChatGptConfig.STREAM,
                messages
                //ChatGptConfig.TOP_P
        ));

        String contents = test.getChoices().get(0).getMessage().getContent(); // "#A, #B" 형태


        return contents;
    }


    public String getQuestion(User user){
        List<String> fields = getHobbyAndPersonality(user);
        String question = "취미는" + fields.get(0) + "," + fields.get(1) + "이고 "
                + "성격은" + fields.get(2) + "," +fields.get(3) + "인데"
                + "#조용한식사,#취미공유,#맛집탐방,#자기계발,#왁자지껄중에추천하는모임두개?단답으로";
        System.out.println(question);

        return question;
    }

    public List<String> getHobbyAndPersonality(User user){
        Hobby hobby = hobbyRepository.findByUser(user);
        Personality personality = personalityRepository.findByUser(user);

        // value가 true인 key를 list로 반환
        List<String> hobbyFields = getFieldsWithIntValueOne(hobby);
        List<String> personalityFields = getFieldsWithIntValueOne(personality);

        if (hobbyFields.size() < 2 || personalityFields.size() < 2 ) {
            throw new IllegalArgumentException("Invalid number of values requested");
        }

        // 랜덤값 선택
        Random random = new Random();
        int randomNum1 = random.nextInt(Math.min(hobbyFields.size(), personalityFields.size()));
        int randomNum2 = random.nextInt(Math.min(hobbyFields.size(), personalityFields.size()));

        // 해당하는 인덱스 값 추출
        List<String> finalFields = List.of(hobbyFields.get(randomNum1), hobbyFields.get(randomNum2)
                , personalityFields.get(randomNum1), personalityFields.get(randomNum2));

        return finalFields;
    }

    public static List<String> getFieldsWithIntValueOne(Object entity) {
        List<String> fieldsWithValueOne = new ArrayList<>();
        Class<?> entityClass = entity.getClass();

        for (Field field : entityClass.getDeclaredFields()) {
            if (field.getType().equals(int.class) && hasIntValueOne(entity, field)) {
                fieldsWithValueOne.add(field.getName());
            }
        }

        return fieldsWithValueOne;
    }

    private static boolean hasIntValueOne(Object entity, Field field) {
        try {
            field.setAccessible(true);
            Integer value = (Integer) field.get(entity);
            return value != null && value == 1;
        } catch (IllegalAccessException e) {
            // 예외 처리 로직 추가
            return false;
        }
    }





}
