package com.bapMate.bapMateServer.domain.participation.service;

import com.bapMate.bapMateServer.domain.participation.entity.Participation;
import com.bapMate.bapMateServer.domain.participation.repository.ParticipationRepository;
import com.bapMate.bapMateServer.domain.user.entity.User;
import com.bapMate.bapMateServer.global.exception.GlobalErrorCode;
import com.bapMate.bapMateServer.global.exception.base.BaseException;
import com.bapMate.bapMateServer.global.response.ErrorResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;

import java.util.Optional;

import static com.bapMate.bapMateServer.global.exception.GlobalErrorCode.INVALID_TOKEN;

@Service
@AllArgsConstructor
public class ParticipationService {
    private final ParticipationRepository participationRepository;
    public Participation getParticipation(User user) {
        Optional<Participation> participation = participationRepository.findById(user.getId());
        if(user.getId() == null) {
            throw new IllegalArgumentException("존재하지 않는 유저입니다");
        }
        return participation.get();
    }
}
