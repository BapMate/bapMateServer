package com.bapMate.bapMateServer.domain.user.usecase.process;

import com.bapMate.bapMateServer.domain.user.exception.NotSupportedLoginTypeException;
import com.bapMate.bapMateServer.global.annotation.Processor;
import com.bapMate.bapMateServer.global.exception.IncorrectIssuerTokenException;
import com.bapMate.bapMateServer.global.feign.oauth.kakao.KakaoProperties;
import com.bapMate.bapMateServer.global.feign.oauth.kakao.oidc.PublicKeyDto;
import com.bapMate.bapMateServer.global.feign.oauth.kakao.oidc.PublicKeysDto;
import com.bapMate.bapMateServer.global.jwt.JwtIdTokenProvider;
import com.bapMate.bapMateServer.global.jwt.dto.UserInfoFromIdToken;
import lombok.RequiredArgsConstructor;

@Processor
@RequiredArgsConstructor
public class LoginByIdTokenProcessor {
    private final PublicKeyProcessor publicKeyProcessor;
    private final JwtIdTokenProvider jwtIdTokenProvider;

    private final KakaoProperties kakaoProperties;

    public UserInfoFromIdToken execute(String loginType, String idToken){
        String kid = jwtIdTokenProvider.getKid(idToken);
        PublicKeysDto publicKeys = new PublicKeysDto();
        String iss = new String();
        String aud = new String();
        switch (loginType) {
            case "kakao":
                PublicKeysDto keys = publicKeyProcessor.getCachedKakaoPublicKeys();
                publicKeys = keys;
                iss = kakaoProperties.getIss();
                aud = kakaoProperties.getAppKey();
                System.out.println(iss);
                System.out.println(aud);
                break;
            case "google":

                break;
            default:
                throw new NotSupportedLoginTypeException();
        }
        PublicKeyDto key = publicKeys.getKeys().stream()
                .filter(k -> k.getKid().equals(kid))
                .findFirst()
                .orElseThrow(() -> new IncorrectIssuerTokenException());
        System.out.println(iss);
        System.out.println(aud);
        return jwtIdTokenProvider.getUserInfo(idToken, publicKeyProcessor.generatePublicKey(key), iss, aud);
    }

}