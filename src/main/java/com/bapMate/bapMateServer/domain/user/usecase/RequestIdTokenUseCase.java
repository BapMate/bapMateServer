package com.bapMate.bapMateServer.domain.user.usecase;

import com.bapMate.bapMateServer.domain.user.dto.response.IdTokenDto;
import com.bapMate.bapMateServer.domain.user.exception.NotSupportedLoginTypeException;
import com.bapMate.bapMateServer.global.annotation.UseCase;
import com.bapMate.bapMateServer.global.feign.oauth.kakao.KakaoProperties;
import com.bapMate.bapMateServer.global.feign.oauth.kakao.RequestKakaoTokenClient;
import com.bapMate.bapMateServer.global.feign.oauth.kakao.dto.KakaoTokenInfoDto;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class RequestIdTokenUseCase {
    private final RequestKakaoTokenClient requestKakaoTokenClient;
    private final KakaoProperties kakaoProperties;
    public IdTokenDto execute(String loginType, String code){
        switch (loginType) {
            case "kakao":
                KakaoTokenInfoDto kakaoTokenInfoDto = requestKakaoTokenClient.getToken(
                        kakaoProperties.getClientId(),
                        kakaoProperties.getRedirectUrl(),
                        code,
                        kakaoProperties.getClientSecret());
                return IdTokenDto.of(kakaoTokenInfoDto.getIdToken());
            case "google":
                break;
        }
        throw new NotSupportedLoginTypeException();
    }
}