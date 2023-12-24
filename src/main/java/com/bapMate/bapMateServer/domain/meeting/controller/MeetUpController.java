package com.bapMate.bapMateServer.domain.meeting.controller;

import com.bapMate.bapMateServer.domain.meeting.dto.request.MeetUpRequestDto;
import com.bapMate.bapMateServer.domain.meeting.entity.MeetUp;
import com.bapMate.bapMateServer.domain.meeting.service.MeetUpService;
import com.bapMate.bapMateServer.domain.participation.entity.Participation;
import com.bapMate.bapMateServer.domain.participation.repository.ParticipationRepository;
import com.bapMate.bapMateServer.domain.participation.service.ParticipationService;
import com.bapMate.bapMateServer.domain.user.entity.User;
import com.bapMate.bapMateServer.global.exception.dto.ErrorReason;
import com.bapMate.bapMateServer.global.response.SuccessResponse;
import com.bapMate.bapMateServer.global.utils.AuthentiatedUserUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
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

    @Operation(description = "모임 대표 이미지")
    @PostMapping(value = "/image", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public SuccessResponse<Object> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        try {
            String imageUrl = meetUpService.uploadImage(file);
            SuccessResponse<Object> successResponse = SuccessResponse.onSuccess(200,imageUrl);
            return successResponse;
        } catch (IOException e) {
            throw  new IllegalArgumentException("오류");
        }
    }

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
    public SuccessResponse<List<MeetUp>> getCreatedMeetUp() {
        User user = authentiatedUserUtils.getCurrentUser();

        List<MeetUp> participation = participationService.getParticipation(user);
        System.out.println(participation.size());
        System.out.println(participation.get(0).getName());
        SuccessResponse<List<MeetUp>> successResponse = SuccessResponse.onSuccess(200, participation);
        System.out.println(successResponse.getData());
        return successResponse;
    }

    @Operation(summary = "모든 모임에서 특정 id값으로 상세정보 보기")
    @GetMapping("/hosts/{meetUpId}")
    public SuccessResponse<MeetUp> getAllMeetUpById(@RequestParam Long meetUpId) {

        MeetUp meetUp = meetUpService.getMeetUpById(meetUpId);
        SuccessResponse<MeetUp> successResponse = SuccessResponse.onSuccess(200, meetUp);
        System.out.println(successResponse.getData());
        return successResponse;
    }

    @Operation(summary = "참여한 모임을 확인합니다.")
    @GetMapping("/participate")
    public SuccessResponse<List<MeetUp>> getParticipatedMeetUp() {
        User user = authentiatedUserUtils.getCurrentUser();

        List<MeetUp> participation = participationService.getParticipations(user);

        SuccessResponse<List<MeetUp>> successResponse = SuccessResponse.onSuccess(200, participation);
        System.out.println(successResponse.getData());
        return successResponse;
    }

    @Operation(summary = "모임에 참여합니다")
    @PostMapping("/participate/{meetUpId}")
    public SuccessResponse<Object> participateMeetUp(@RequestParam Long meetUpId){
        User user = authentiatedUserUtils.getCurrentUser();

        meetUpService.participateMeetUp(user,meetUpId);

        SuccessResponse<Object> successResponse = SuccessResponse.onSuccess(200);
        return successResponse;
    }



}
