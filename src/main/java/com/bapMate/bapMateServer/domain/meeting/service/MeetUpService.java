package com.bapMate.bapMateServer.domain.meeting.service;

import com.bapMate.bapMateServer.domain.meeting.dto.request.MeetUpRequestDto;
import com.bapMate.bapMateServer.domain.meeting.entity.MeetUp;
import com.bapMate.bapMateServer.domain.meeting.enums.MeetUpStatus;
import com.bapMate.bapMateServer.domain.meeting.repository.MeetUpRepository;
import com.bapMate.bapMateServer.domain.participation.entity.Participation;
import com.bapMate.bapMateServer.domain.participation.repository.ParticipationRepository;
import com.bapMate.bapMateServer.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class MeetUpService {
    private final ParticipationRepository participationRepository;
    private final MeetUpRepository meetUpRepository;
    public void uploadMeetUp(User user, MeetUpRequestDto requestDto) {
        MeetUp meetUp = requestDto.toEntity();
        meetUpRepository.save(meetUp);
        Participation participation = Participation.builder()
                .meetUp(meetUp)
                .user(user)
                .meetUpStatus(MeetUpStatus.HOST.getStatus())
                .build();
        participationRepository.save(participation);
    }
}
