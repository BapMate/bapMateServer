package com.bapMate.bapMateServer.domain.meeting.controller;

import com.bapMate.bapMateServer.domain.meeting.dto.request.MeetUpRequestDto;
import com.bapMate.bapMateServer.domain.meeting.service.MeetUpService;
import com.bapMate.bapMateServer.domain.participation.entity.Participation;
import com.bapMate.bapMateServer.domain.participation.repository.ParticipationRepository;
import com.bapMate.bapMateServer.domain.participation.service.ParticipationService;
import com.bapMate.bapMateServer.domain.user.entity.User;
import com.bapMate.bapMateServer.global.response.SuccessResponse;
import com.bapMate.bapMateServer.global.utils.AuthentiatedUserUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/meetUp")
@Tag(name = "모임 관련된 api", description = "모임 관련된 api")
@SecurityRequirement(name = "access-token")
public class MeetUpController {
    private final MeetUpService meetUpService;
    private final ParticipationService participationService;
    private final AuthentiatedUserUtils authentiatedUserUtils;
    @Operation(summary = "모임을 생성합니다.")
    @PostMapping("/host")
    public SuccessResponse<Object> createMeetUp(@RequestBody MeetUpRequestDto meetUpRequestDto) {
        User user = authentiatedUserUtils.getCurrentUser();

        meetUpService.uploadMeetUp(user, meetUpRequestDto);

        SuccessResponse<Object> successResponse = SuccessResponse.onSuccess(200);
        return successResponse;
    }
    @Operation(summary = "생성한 모임을 확인합니다.")
    @GetMapping("/host")
    public SuccessResponse<Object> getCreatedMeetUp() {
        User user = authentiatedUserUtils.getCurrentUser();

        Participation participation = participationService.getParticipation(user);
        SuccessResponse<Object> successResponse = SuccessResponse.onSuccess(200,participation);

        return successResponse;
    }

}
