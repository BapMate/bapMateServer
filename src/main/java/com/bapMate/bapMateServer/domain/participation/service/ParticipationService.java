package com.bapMate.bapMateServer.domain.participation.service;

import com.bapMate.bapMateServer.domain.meeting.entity.MeetUp;
import com.bapMate.bapMateServer.domain.meeting.enums.MeetUpStatus;
import com.bapMate.bapMateServer.domain.participation.entity.Participation;
import com.bapMate.bapMateServer.domain.participation.repository.ParticipationRepository;
import com.bapMate.bapMateServer.domain.user.entity.User;
import com.bapMate.bapMateServer.global.exception.GlobalErrorCode;
import com.bapMate.bapMateServer.global.exception.base.BaseException;
import com.bapMate.bapMateServer.global.response.ErrorResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.bapMate.bapMateServer.global.exception.GlobalErrorCode.INVALID_TOKEN;

@Service
@AllArgsConstructor
public class ParticipationService {
    private final ParticipationRepository participationRepository;
    public List<MeetUp> getParticipation(User user) {
        List<Participation> participation = participationRepository.findAllByUserId(user.getId());
        if(user.getId() == null) {
            throw new IllegalArgumentException("존재하지 않는 유저입니다");
        }
        List<MeetUp> hostMeetUps = participation.stream()
                .filter(meet -> "HOST".equals(meet.getMeetUpStatus()))
                .map(Participation::getMeetUp)
                .distinct() // Ensure uniqueness
                .collect(Collectors.toList());
        System.out.println(hostMeetUps.size());
        return hostMeetUps;
    }
}
