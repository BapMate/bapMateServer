package com.bapMate.bapMateServer.domain.user.repositroy;

import com.bapMate.bapMateServer.domain.user.entity.RefreshToken;
import com.bapMate.bapMateServer.global.RedisRepository;
import com.bapMate.bapMateServer.global.redis.BaseRedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import static com.bapMate.bapMateServer.global.constant.StaticValue.REFRESH_TOKEN_KEY;

@RedisRepository
public class RefreshTokenRedisDao extends BaseRedisRepository<RefreshToken> {
    @Autowired
    public RefreshTokenRedisDao(RedisTemplate redisTemplate) {
        this.prefix = REFRESH_TOKEN_KEY + ":";
        this.redisTemplate = redisTemplate;
    }
}