package com.bapMate.bapMateServer.domain.chatgpt.controller;

import com.bapMate.bapMateServer.domain.chatgpt.dto.response.ChatGptResponseDto;
import com.bapMate.bapMateServer.domain.chatgpt.service.ChatGptService;
import com.bapMate.bapMateServer.domain.meeting.service.MeetUpService;
import com.bapMate.bapMateServer.domain.user.entity.User;
import com.bapMate.bapMateServer.global.utils.AuthentiatedUserUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/chat-gpt")
@RequiredArgsConstructor
@Tag(name = "Chatgpt 관련된 api", description = "Chatgpt 관련된 api")
@SecurityRequirement(name = "access-token")
public class ChatgptController {

    private final ChatGptService chatGptService;
    private final MeetUpService meetUpService;
    private final AuthentiatedUserUtils authentiatedUserUtils;

    @Operation(summary = "모임을 추천합니다.")
    @GetMapping("/meeting")
    public ResponseEntity recommendMeetings() {
        User user = authentiatedUserUtils.getCurrentUser();
        String contents = chatGptService.askQuestion(user);
        List<String> content = Arrays.asList(contents.split(", "));
        return meetUpService.chatGptMeetUp(content);
    }

}
