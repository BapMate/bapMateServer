package com.bapMate.bapMateServer.domain.meeting.service;

import com.bapMate.bapMateServer.domain.meeting.dto.request.MeetUpRequestDto;
import com.bapMate.bapMateServer.domain.meeting.entity.MeetUp;
import com.bapMate.bapMateServer.domain.meeting.enums.MeetUpStatus;
import com.bapMate.bapMateServer.domain.meeting.exception.FullCapacityException;
import com.bapMate.bapMateServer.domain.meeting.exception.MeetingNotFoundException;
import com.bapMate.bapMateServer.domain.meeting.repository.MeetUpRepository;
import com.bapMate.bapMateServer.domain.participation.entity.Participation;
import com.bapMate.bapMateServer.domain.participation.repository.ParticipationRepository;
import com.bapMate.bapMateServer.domain.user.entity.User;
import com.bapMate.bapMateServer.global.exception.handler.GlobalExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.ErrorResponseException;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MeetUpService {
    private final ParticipationRepository participationRepository;
    private final MeetUpRepository meetUpRepository;

    public MeetUp uploadMeetUp(User user, MeetUpRequestDto requestDto) {
        MeetUp meetUp = turnCheckToOne(requestDto);
        Participation participation = Participation.builder()
                .meetUp(meetUp)
                .user(user)
                .meetUpStatus(MeetUpStatus.HOST.getStatus())
                .build();
        participationRepository.save(participation);
        //addParticipant(meetUp, participation);
        return meetUp;
    }

//    private void addParticipant(MeetUp meetUp, Participation participation) {
//        meetUp.addParticipation(participation);
//        meetUpRepository.save(meetUp);
//    }

    private MeetUp turnCheckToOne(MeetUpRequestDto requestDto) {
        MeetUp meetUp = requestDto.toEntity();
        meetUpRepository.save(meetUp);
        System.out.println(meetUp.getName());
        return meetUp;
    }

    public Participation participateMeetUp(User user, Long meetUpId) {
        //meetUpId를 통해 meetUp가져오기 - NotFound
        MeetUp meetUp = meetUpRepository.findById(meetUpId).orElseThrow(() -> new MeetingNotFoundException());

        //meetUp의 count 갱신하기 다 찼으면 에러 발생시키기 -FullCapacity
        int number = meetUp.getCurrentNumberOfPeople();
        if(!(number < meetUp.getNumberOfPeople())){
           throw new FullCapacityException();
        }
        meetUp.updateNumberOfPeople(number);



        //participation을 meetUp에 추가하기
        Participation participation = Participation.builder()
                .meetUp(meetUp)
                .user(user)
                .meetUpStatus(MeetUpStatus.PARTICIPANT.getStatus())
                .build();

        meetUp.addParticipation(participation);

        //meetUp에 저장하고
        meetUpRepository.save(meetUp);

        participationRepository.save(participation);
        return participation;
    }
}
