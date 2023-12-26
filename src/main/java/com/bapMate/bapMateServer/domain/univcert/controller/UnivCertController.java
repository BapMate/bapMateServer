package com.bapMate.bapMateServer.domain.univcert.controller;

import com.bapMate.bapMateServer.domain.univcert.dto.UnivCodeRequestDto;
import com.bapMate.bapMateServer.domain.univcert.dto.UnivRequestDto;
import com.bapMate.bapMateServer.domain.univcert.service.UnivService;
import com.bapMate.bapMateServer.domain.user.entity.User;
import com.bapMate.bapMateServer.global.response.SuccessResponse;
import com.bapMate.bapMateServer.global.utils.AuthentiatedUserUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/univcert")
@Tag(name = "학교 인증 api", description = "학교 인증 api")
@SecurityRequirement(name = "access-token")
public class UnivCertController {
    private final UnivService univService;
    private final AuthentiatedUserUtils authentiatedUserUtils;


    @Operation(description = "학교 인증")
    @PostMapping
    public SuccessResponse<String> univCertCertify(@RequestBody UnivRequestDto univRequestDto)  throws IOException {
        return univService.univCertCertify(univRequestDto);
    }

    @Operation(description = "학교 인증 코드")
    @PostMapping("/code")
    public SuccessResponse univCertifyCode(@RequestBody UnivCodeRequestDto univCodeRequestDto) throws IOException {
        User user = authentiatedUserUtils.getCurrentUser();
        return univService.univCertCertifyCode(univCodeRequestDto, user);
    }


}
