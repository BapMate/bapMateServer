package com.bapMate.bapMateServer.domain.meeting.service;

import com.bapMate.bapMateServer.domain.meeting.dto.request.MeetUpRequestDto;
import com.bapMate.bapMateServer.domain.meeting.dto.response.MeetUpChatGptResponseDto;
import com.bapMate.bapMateServer.domain.meeting.dto.response.MeetUpResponseDto;
import com.bapMate.bapMateServer.domain.meeting.entity.MeetUp;
import com.bapMate.bapMateServer.domain.meeting.enums.MeetUpStatus;
import com.bapMate.bapMateServer.domain.meeting.exception.FullCapacityException;
import com.bapMate.bapMateServer.domain.meeting.exception.MeetingNotFoundException;
import com.bapMate.bapMateServer.domain.meeting.exception.NotAllowedToParticipateException;
import com.bapMate.bapMateServer.domain.meeting.repository.MeetUpRepository;
import com.bapMate.bapMateServer.domain.participation.entity.Participation;
import com.bapMate.bapMateServer.domain.participation.repository.ParticipationRepository;
import com.bapMate.bapMateServer.domain.participation.service.ParticipationService;
import com.bapMate.bapMateServer.domain.user.entity.User;
import com.bapMate.bapMateServer.domain.user.exception.UserNotFoundException;
import com.bapMate.bapMateServer.global.S3.S3Service;
import com.bapMate.bapMateServer.global.exception.handler.GlobalExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MeetUpService {
    private final ParticipationRepository participationRepository;
    private final MeetUpRepository meetUpRepository;
    private final ParticipationService participationService;
    private final S3Service s3UploadService;

    //@Transactional
    public String uploadImage(MultipartFile file) throws IOException {
        // S3에 이미지 파일 업로드 및 업로드된 파일의 URL 생성
        String imageUrl = s3UploadService.upload(file);
//        MeetUp meetUp = meetUpRepository.findById(meetUpId).orElseThrow(UserNotFoundException::new);
//
//        meetUp.updateImgUrl(imageUrl);
//        meetUpRepository.save(meetUp);
        return imageUrl;
    }

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

    private MeetUp turnCheckToOne(MeetUpRequestDto requestDto) {
        MeetUp meetUp = requestDto.toEntity();
        meetUpRepository.save(meetUp);
        System.out.println(meetUp.getName());
        return meetUp;
    }



    @Transactional
    public Participation participateMeetUp(User user, Long meetUpId) {
        //meetUpId를 통해 meetUp가져오기 - NotFound
        MeetUp meetUp = meetUpRepository.findById(meetUpId).orElseThrow(() -> new MeetingNotFoundException());

        //meetUp과 User랑 연결된 Participant가져오고 중복 검증까지 하기
        validate(user,meetUpId,meetUp);

        Participation participation = addParticipation(meetUp,user);

        return participation;
    }

    @Transactional
    public Participation addParticipation(MeetUp meetUp, User user) {
        //participation을 meetUp에 추가하기
        Participation participation = Participation.builder()
                .meetUp(meetUp)
                .user(user)
                .meetUpStatus(MeetUpStatus.PARTICIPANT.getStatus())
                .build();
        participationRepository.save(participation);
        return participation;
    }

    private void validate(User user,Long meetUpId,MeetUp meetUp) {

        validateDuplicateHost(user, meetUpId);
        validateDuplicateParticipant(user,meetUpId);
        validateCapacity(meetUp,meetUpId);
    }

    @Transactional
    public void validateCapacity(MeetUp meetUp, Long meetUpId) {
        //meetUp의 count 갱신하기 다 찼으면 에러 발생시키기 -FullCapacity
        int number = meetUp.getCurrentNumberOfPeople();
        if(!(number < meetUp.getNumberOfPeople())){
            throw new FullCapacityException();
        }
        participateMeetUp(meetUpId);
    }

    private void validateDuplicateParticipant(User user, Long meetUpId) {
        participationService.checkExistence(user,meetUpId);
    }

    private void validateDuplicateHost(User user, Long meetUpId) {
        List<Participation> participations = participationRepository.findAllByUserId(user.getId());
        //meetUp을 가져와 meetUp과 매핑된 participant가져와
//meetUp을 가져와 meetUp과 매핑된 participant를 가져옵니다.
        Optional<Long> hostUserId = participationService.getHostUserId(participations, meetUpId);

// hostUserId가 존재하고, 그 값이 현재 사용자의 ID와 같다면 예외를 던집니다.
        hostUserId.ifPresent(id -> {
            if (id.equals(user.getId())) {
                throw new NotAllowedToParticipateException();
            }
        });
    }

    @Transactional
    public void participateMeetUp(Long meetUpId) {
        meetUpRepository.incrementCurrentNumberOfPeople(meetUpId);
    }

    public ResponseEntity<?> chatGptMeetUp(List<String> meetUps) {
        List<MeetUp> meetUpList = new ArrayList<>();

        // Assuming meetUpRepository.findByMeetUpAtmosphere returns Optional<MeetUp>
        Optional<MeetUp> optionalMeetUp0 = meetUpRepository.findByMeetUpAtmosphere(meetUps.get(0));
        optionalMeetUp0.ifPresent(meetUpList::add);

        Optional<MeetUp> optionalMeetUp1 = meetUpRepository.findByMeetUpAtmosphere(meetUps.get(1));
        optionalMeetUp1.ifPresent(meetUpList::add);

        MeetUpChatGptResponseDto responseDTO = new MeetUpChatGptResponseDto(meetUpList, "Meetups retrieved successfully");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
