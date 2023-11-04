package com.bapMate.bapMateServer.domain.user.service;

import com.bapMate.bapMateServer.domain.user.adapter.UserAdaptor;
import com.bapMate.bapMateServer.domain.user.entity.AuthInfo;
import com.bapMate.bapMateServer.domain.user.entity.LoginType;
import com.bapMate.bapMateServer.domain.user.entity.Profile;
import com.bapMate.bapMateServer.domain.user.entity.User;
import com.bapMate.bapMateServer.domain.user.exception.EmailAlreadyRegistered;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserDomainService {
    private final UserAdaptor userAdaptor;


    public User login(LoginType loginType, String email){
        User user = userAdaptor.findByEmail(email);
        if(user.getAuthInfo().getLoginType().equals(loginType)){
            return user;
        }else throw new EmailAlreadyRegistered();
    }

    @Transactional
    public User signUp(AuthInfo authInfo){
        User user = User.builder()
                .authInfo(authInfo)
                .build();
        System.out.println(user.getProfile());
        System.out.println(user.getAuthInfo());
        return userAdaptor.save(user);
    }

}