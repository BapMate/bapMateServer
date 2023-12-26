package com.bapMate.bapMateServer.domain.user.usecase;

import com.bapMate.bapMateServer.domain.user.adapter.UserAdaptor;
import com.bapMate.bapMateServer.domain.user.dto.response.AccountTokenDto;
import com.bapMate.bapMateServer.domain.user.entity.LoginType;
import com.bapMate.bapMateServer.domain.user.entity.User;
import com.bapMate.bapMateServer.domain.user.exception.UniversityNotAuthenticated;
import com.bapMate.bapMateServer.domain.user.service.UserDomainService;
import com.bapMate.bapMateServer.domain.user.usecase.process.GenerateAccountTokenProcessor;
import com.bapMate.bapMateServer.domain.user.usecase.process.LoginByIdTokenProcessor;
import com.bapMate.bapMateServer.global.annotation.UseCase;
import com.bapMate.bapMateServer.global.jwt.dto.UserInfoFromIdToken;
import com.bapMate.bapMateServer.global.response.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

@UseCase
@RequiredArgsConstructor
public class LoginUseCase {
    private final LoginByIdTokenProcessor loginByIdTokenProcessor;
    private final UserAdaptor userAdaptor;
    private final UserDomainService userDomainService;
    private final GenerateAccountTokenProcessor generateAccountTokenProcessor;

    public AccountTokenDto execute(String loginType, String idToken){
        UserInfoFromIdToken userInfo = loginByIdTokenProcessor.execute(loginType, idToken);
        if (!userAdaptor.checkEmail(userInfo.getEmail())){
            return AccountTokenDto.notRegistered();
        }

        validateUniversity(userInfo.getEmail());
        User user = userDomainService.login(LoginType.fromValue(loginType), userInfo.getEmail());
        return generateAccountTokenProcessor.createToken(user);
    }

    private ResponseEntity<ErrorResponse> validateUniversity(String email) {
        User user = userAdaptor.findByEmail(email);
        if(!user.getUniversityIsAuthenticated()){
            throw UniversityNotAuthenticated.EXCEPTION;
        };
        return null;
    }
}
