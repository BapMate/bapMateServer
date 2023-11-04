package com.bapMate.bapMateServer.domain.user.usecase;

import com.bapMate.bapMateServer.domain.user.adapter.UserAdaptor;
import com.bapMate.bapMateServer.domain.user.dto.response.AccountTokenDto;
import com.bapMate.bapMateServer.domain.user.entity.User;
import com.bapMate.bapMateServer.domain.user.service.RefreshTokenRedisService;
import com.bapMate.bapMateServer.domain.user.usecase.process.GenerateAccountTokenProcessor;
import com.bapMate.bapMateServer.global.annotation.UseCase;
import com.bapMate.bapMateServer.global.exception.InvalidTokenException;
import com.bapMate.bapMateServer.global.jwt.JwtProvider;
import com.bapMate.bapMateServer.global.jwt.dto.DecodedJwtToken;
import lombok.RequiredArgsConstructor;

import static com.bapMate.bapMateServer.global.constant.StaticValue.REFRESH_TOKEN;

@UseCase
@RequiredArgsConstructor
public class RefreshTokenUseCase {
    private final RefreshTokenRedisService refreshTokenRedisService;
    private final GenerateAccountTokenProcessor generateAccountTokenProcessor;
    private final JwtProvider jwtProvider;
    private final UserAdaptor userAdaptor;
    public AccountTokenDto execute(String refreshToken){
        DecodedJwtToken decodedJwtToken = jwtProvider.decodeToken(refreshToken,REFRESH_TOKEN);
        User user = userAdaptor.findById(decodedJwtToken.getUserId());

        if(refreshTokenRedisService.checkToken(user.getId(), refreshToken)){
            return generateAccountTokenProcessor.refreshToken(user,refreshToken);
        }else throw new InvalidTokenException();
    }
}