package com.bapMate.bapMateServer.domain.univcert.service;

import com.bapMate.bapMateServer.domain.univcert.dto.UnivCodeRequestDto;
import com.bapMate.bapMateServer.domain.univcert.dto.UnivRequestDto;
import com.bapMate.bapMateServer.domain.user.entity.User;
import com.bapMate.bapMateServer.domain.user.exception.UniversityNotAuthenticated;
import com.bapMate.bapMateServer.global.exception.base.BaseException;
import com.bapMate.bapMateServer.global.response.SuccessResponse;
import com.univcert.api.UnivCert;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UnivService {
    private final static boolean univ_check = true;

    @Value("${univcert.api-key}")
    private String univCertAPIkey;

    public SuccessResponse<String> univCertCertify(@RequestBody UnivRequestDto univRequestDto)  throws IOException {
        Map<String, Object> response = UnivCert.certify(univCertAPIkey, univRequestDto.getUnivEmail(), univRequestDto.getUnivName(), univ_check);
        validate(response);
        return SuccessResponse.onSuccess(200,"인증코드가 이메일로 전송되었습니다");
    }

    private ResponseEntity<String> validate(Map<String, Object> response) {
        boolean success = (boolean) response.get("success");
        String message = (String) response.get("message");
        if(!success){
            throw new IllegalArgumentException(message);
        }
        return null;
    }

    @Transactional
    public SuccessResponse univCertCertifyCode(UnivCodeRequestDto univCodeRequestDto, User user) throws IOException {
        Map<String, Object> response = UnivCert.certifyCode(univCertAPIkey,
                univCodeRequestDto.getUnivEmail(), univCodeRequestDto.getUnivName(), univCodeRequestDto.getCode());
        validateResponse(response, user);
        return SuccessResponse.onSuccess(200,"인증이 성공하였습니다");
    }


    private BaseException validateResponse(Map<String, Object> response, User user) {
        boolean success = (boolean) response.get("success");
        String message = (String) response.get("message");
        if(success){
            user.authenticateUniversity();
        }
        else{
            throw new IllegalArgumentException(message);
        }
        return null;
    }
}
