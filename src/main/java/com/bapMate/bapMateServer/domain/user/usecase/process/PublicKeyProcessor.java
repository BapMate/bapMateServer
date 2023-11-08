package com.bapMate.bapMateServer.domain.user.usecase.process;

import com.bapMate.bapMateServer.global.annotation.Processor;
import com.bapMate.bapMateServer.global.feign.oauth.kakao.RequestKakaoTokenClient;
import com.bapMate.bapMateServer.global.feign.oauth.kakao.oidc.PublicKeyDto;
import com.bapMate.bapMateServer.global.feign.oauth.kakao.oidc.PublicKeysDto;
import com.bapMate.bapMateServer.global.utils.PublicKeyGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;

import java.security.interfaces.RSAPublicKey;

import static com.bapMate.bapMateServer.global.constant.StaticValue.KAKAO_PUBLIC_KEYS;

@Processor
@RequiredArgsConstructor
public class PublicKeyProcessor {
    private final RequestKakaoTokenClient requestKakaoTokenClient;

    @Cacheable(value = KAKAO_PUBLIC_KEYS, cacheManager = "redisCacheManager")
    public PublicKeysDto getCachedKakaoPublicKeys(){
        return requestKakaoTokenClient.getPublicKeys();
    }

    public RSAPublicKey generatePublicKey(PublicKeyDto key){
        return PublicKeyGenerator.excute(key.getKty(), key.getN(), key.getE());
    }
}