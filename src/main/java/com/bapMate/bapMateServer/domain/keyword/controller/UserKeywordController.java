package com.bapMate.bapMateServer.domain.keyword.controller;

import com.bapMate.bapMateServer.domain.keyword.dto.request.EatingRequestDto;
import com.bapMate.bapMateServer.domain.keyword.dto.request.HobbyRequestDto;
import com.bapMate.bapMateServer.domain.keyword.dto.request.PersonalityRequestDto;
import com.bapMate.bapMateServer.domain.keyword.service.UserKeywordService;
import com.bapMate.bapMateServer.domain.user.entity.User;
import com.bapMate.bapMateServer.global.response.SuccessResponse;
import com.bapMate.bapMateServer.global.utils.AuthentiatedUserUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/auth")
@Tag(name = "사용자 키워드 받는 api", description = "사용자 키워드 받는 api")
@SecurityRequirement(name = "access-token")
public class UserKeywordController {
    private final UserKeywordService userKeywordService;
    private final AuthentiatedUserUtils authentiatedUserUtils;

    @Operation(summary = "사용자의 취미 키워드를 받습니다.")
    @PostMapping("/userHobby")
    public SuccessResponse<Object> setUserHobby(@Valid @RequestBody HobbyRequestDto requestDto) {

        User user = authentiatedUserUtils.getCurrentUser(); // 유저 정보 가져오기
        userKeywordService.setUserHobby(user, requestDto);

        SuccessResponse<Object> successResponse = SuccessResponse.onSuccess(200);
        return successResponse;
    }

    @Operation(summary = "사용자의 성격 키워드를 받습니다.")
    @PostMapping("/userPersonality")
    public SuccessResponse<Object> setUserPersonality(@Valid @RequestBody PersonalityRequestDto requestDto) {

        User user = authentiatedUserUtils.getCurrentUser(); // 유저 정보 가져오기
        userKeywordService.setUserPersonality(user, requestDto);

        SuccessResponse<Object> successResponse = SuccessResponse.onSuccess(200);
        return successResponse;
    }

    @Operation(summary = "사용자의 식사량, 속도 키워드를 받습니다.")
    @PostMapping("/userEating")
    public SuccessResponse<Object> setUserEating(@Valid @RequestBody EatingRequestDto requestDto) {

        User user = authentiatedUserUtils.getCurrentUser(); // 유저 정보 가져오기
        userKeywordService.setUserEating(user, requestDto);

        SuccessResponse<Object> successResponse = SuccessResponse.onSuccess(200);
        return successResponse;
    }
}
