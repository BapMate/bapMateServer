package com.bapMate.bapMateServer.domain.meeting.service;

import com.bapMate.bapMateServer.domain.meeting.dto.request.MeetUpRequestDto;
import com.bapMate.bapMateServer.domain.meeting.dto.response.MeetUpChatGptResponseDto;
import com.bapMate.bapMateServer.domain.meeting.dto.response.MeetUpResponseDto;
import com.bapMate.bapMateServer.domain.meeting.entity.MeetUp;
import com.bapMate.bapMateServer.domain.meeting.enums.MeetUpAtmosphere;
import com.bapMate.bapMateServer.domain.meeting.enums.MeetUpStatus;
import com.bapMate.bapMateServer.domain.meeting.exception.*;
import com.bapMate.bapMateServer.domain.meeting.repository.MeetUpRepository;
import com.bapMate.bapMateServer.domain.participation.entity.Participation;
import com.bapMate.bapMateServer.domain.participation.repository.ParticipationRepository;
import com.bapMate.bapMateServer.domain.participation.service.ParticipationService;
import com.bapMate.bapMateServer.domain.user.entity.User;
import com.bapMate.bapMateServer.global.S3.S3Service;
import com.bapMate.bapMateServer.global.exception.handler.GlobalExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    private final GlobalExceptionHandler globalException;

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
        System.out.println(requestDto.getMeetUpAtmosphere());
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

    public MeetUpResponseDto getMeetUpById(Long meetUpId) {
        Optional<MeetUp> meetUp = meetUpRepository.findById(meetUpId);

        if(!meetUp.isPresent() ){
            throw MeetingNotFoundException.EXCEPTION;
        }
        MeetUp meetUpData = meetUp.get();
        MeetUpResponseDto responseDto = changeToDto(meetUpData);
        return responseDto;
    }

    private MeetUpResponseDto changeToDto(MeetUp meetUpData) {
        MeetUpResponseDto meetUpResponseDto = MeetUpResponseDto.builder()
                .meetUpAtmosphere(meetUpData.getMeetUpAtmosphere().getTitle())
                .numberOfPeople(meetUpData.getNumberOfPeople())
                .date(meetUpData.getDate())
                .region(meetUpData.getRegion())
                .chatRoomLink(meetUpData.getChatRoomLink())
                .id(meetUpData.getId())
                .restaurant(meetUpData.getRestaurant())
                .representationImage(meetUpData.getRepresentationImage())
                .regionAtmosphere(meetUpData.getRegionAtmosphere().getTitle())
                .name(meetUpData.getName())
                .introduce(meetUpData.getIntroduce())
                .currentNumberOfPeople(meetUpData.getCurrentNumberOfPeople())
                .build();
        return meetUpResponseDto;
    }

    public ResponseEntity<?> chatGptMeetUp(List<String> meetUps) {
        List<MeetUpResponseDto> meetUpList = new ArrayList<>();
        for (String meetUp : meetUps) {
            System.out.println(meetUp);
        }


        for (String meetUpStr : meetUps) {
            try {
                MeetUpAtmosphere meetUpAtmosphere = MeetUpAtmosphere.valueOf(meetUpStr);
                System.out.println(meetUpAtmosphere);
                List<MeetUp> meetUpListForAtmosphere = meetUpRepository.findByMeetUpAtmosphere(meetUpAtmosphere);

                // If there is at least one result, convert each MeetUp to MeetUpResponseDto and add to meetUpList
                if (!meetUpListForAtmosphere.isEmpty()) {
                    meetUpListForAtmosphere.stream()
                            .map(this::changeToDto)
                            .findFirst()
                            .ifPresent(meetUpResponseDto -> meetUpList.add(meetUpResponseDto));
                }
            } catch (IllegalArgumentException e) {
                throw MeetingNotFoundException.EXCEPTION;
            }
        }


        MeetUpChatGptResponseDto responseDTO = new MeetUpChatGptResponseDto(meetUpList, "Meetups retrieved successfully");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
