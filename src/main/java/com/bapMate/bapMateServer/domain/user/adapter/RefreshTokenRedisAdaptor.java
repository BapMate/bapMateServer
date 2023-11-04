package com.bapMate.bapMateServer.domain.user.adapter;

import com.bapMate.bapMateServer.domain.user.entity.RefreshToken;
import com.bapMate.bapMateServer.domain.user.exception.ExpiredRefreshTokenException;
import com.bapMate.bapMateServer.domain.user.repositroy.RefreshTokenRedisDao;
import com.bapMate.bapMateServer.global.annotation.Adaptor;
import lombok.RequiredArgsConstructor;

@Adaptor
@RequiredArgsConstructor
public class RefreshTokenRedisAdaptor {
    private final RefreshTokenRedisDao refreshTokenRedisDao;

    public void save(Long id, RefreshToken refreshToken){
        refreshTokenRedisDao.save(id, refreshToken);
    }
    public RefreshToken findById(Long id){
        return refreshTokenRedisDao.findById(id)
                .orElseThrow(() -> new ExpiredRefreshTokenException());
    }
}