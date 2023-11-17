package com.bapMate.bapMateServer.domain.participation.service;

import com.bapMate.bapMateServer.domain.meeting.entity.MeetUp;
import com.bapMate.bapMateServer.domain.meeting.enums.MeetUpStatus;
import com.bapMate.bapMateServer.domain.meeting.exception.AlreadyParticipatedException;
import com.bapMate.bapMateServer.domain.participation.entity.Participation;
import com.bapMate.bapMateServer.domain.participation.repository.ParticipationRepository;
import com.bapMate.bapMateServer.domain.user.entity.User;
import com.bapMate.bapMateServer.domain.user.exception.UserNotFoundException;
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
        List<Participation> participation = validateUser(user);
        List<MeetUp> hostMeetUps = participation.stream()
                .filter(meet -> "HOST".equals(meet.getMeetUpStatus()))
                .map(Participation::getMeetUp)
                .distinct() // Ensure uniqueness
                .collect(Collectors.toList());
        return hostMeetUps;
    }

    private List<Participation> validateUser(User user) {
        List<Participation> participation = participationRepository.findAllByUserId(user.getId());
        if(user.getId() == null) {
            throw new UserNotFoundException();
        }
        return participation
    }

    public List<MeetUp> getParticipations(User user) {
        List<Participation> participation = validateUser(user);
        List<MeetUp> hostMeetUps = participation.stream()
                .filter(meet -> "PARTICIPANT".equals(meet.getMeetUpStatus()))
                .map(Participation::getMeetUp)
                .distinct() // Ensure uniqueness
                .collect(Collectors.toList());
        System.out.println(hostMeetUps.size());
        return hostMeetUps;
    }

    public Optional<Long> getHostUserId(List<Participation> participationList, Long meetUpId) {
        return participationList.stream()
                .filter(participation -> meetUpId.equals(participation.getMeetUp().getId()))
                .filter(participation -> "HOST".equals(participation.getMeetUpStatus()))
                .map(participation -> participation.getUser().getId())
                .findFirst();
    }

    public void checkExistence(User user, Long meetUpId) {
        List<Participation> participation = validateUser(user);

        // Check if userId is in the list of participating MeetUps
        boolean userIdExists = participation.stream()
                .filter(meet -> meetUpId.equals(meet.getMeetUp().getId()))
                .filter(meet -> "PARTICIPANT".equals(meet.getMeetUpStatus()))
                .anyMatch(meet -> meet.getUser().getId().equals(user.getId()));

        if (userIdExists) {
            throw new AlreadyParticipatedException();
        }
    }
}
