package com.bapMate.bapMateServer.domain;

import com.bapMate.bapMateServer.domain.meeting.dto.request.MeetUpRequestDto;
import com.bapMate.bapMateServer.domain.meeting.entity.MeetUp;
import com.bapMate.bapMateServer.domain.meeting.enums.MeetUpAtmosphere;
import com.bapMate.bapMateServer.domain.meeting.enums.MeetUpStatus;
import com.bapMate.bapMateServer.domain.meeting.enums.RegionAtmosphere;
import com.bapMate.bapMateServer.domain.meeting.exception.FullCapacityException;
import com.bapMate.bapMateServer.domain.meeting.exception.MeetingNotFoundException;
import com.bapMate.bapMateServer.domain.meeting.repository.MeetUpRepository;
import com.bapMate.bapMateServer.domain.meeting.service.MeetUpService;
import com.bapMate.bapMateServer.domain.participation.entity.Participation;
import com.bapMate.bapMateServer.domain.participation.repository.ParticipationRepository;
import com.bapMate.bapMateServer.domain.user.entity.AuthInfo;
import com.bapMate.bapMateServer.domain.user.entity.LoginType;
import com.bapMate.bapMateServer.domain.user.entity.User;
import com.bapMate.bapMateServer.domain.user.repositroy.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.bapMate.bapMateServer.domain.meeting.enums.MeetUpStatus.PARTICIPANT;
import static org.assertj.core.api.ClassBasedNavigableIterableAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Transactional
class MeetUpServiceTest {

//    @InjectMocks
//    private MeetUpService meetUpService;
//
//    @Mock
//    private MeetUpRepository meetUpRepository;
//
//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private ParticipationRepository participationRepository;
//
//    @Test
//    void 모임_생성_성공() {
//        User user = User.builder()
//                .id(1L) // Set the ID as needed
//                .name("John Doe") // Set the name as needed
//                .authInfo(AuthInfo.builder()
//                        .email("john@example.com") // Set the email as needed
//                        .loginType(LoginType.KAKAO) // Set the login type as needed
//                        .build())
//                .universityName("Example University") // Set the university name as needed
//                .build(); // You may need to create a valid user for testing
//        userRepository.save(user);
//
//        MeetUpRequestDto meetUpRequestDto = MeetUpRequestDto.builder()
//                .name("Sample MeetUp") // Set the name as needed
//                .introduce("A sample meet-up for testing") // Set the introduce as needed
//                .chatRoomLink("https://example.com/chat-room") // Set the chat room link as needed
//                .region("Sample Region") // Set the region as needed
//                .date(LocalDateTime.now()) // Set the date as needed
//                .restaurant("Sample Restaurant") // Set the restaurant as needed
//                .numberOfPeople(10) // Set the total number of people as needed
//                .meetUpAtmosphere("#조용한식사") // Set the meet-up atmosphere as needed
//                .regionAtmosphere("#인스타감성") // Set the region atmosphere as needed
//                .representationImage("sample-image.jpg") // Set the representation image as needed
//                .build();
//
//        MeetUp meetUp = meetUpService.uploadMeetUp(user,meetUpRequestDto);
//        System.out.println(meetUp.getId());
//        System.out.println(meetUp.getAllUsers().size());
//        List<Participation> participations = participationRepository.findAll();
//        //System.out.println(participations.size());
//        Optional<Participation> participationA = participationRepository.findByUserId(user.getId());
//        Assertions.assertThat(participationA.isPresent());
//        String meetId = participationA.get().getMeetUpStatus();
//        System.out.println(meetId);
//                //.get().getMeetUp().getId();
//        //Long meetUpId = 1L;
////        System.out.println(meetId);
////
////        Optional<MeetUp> meet = meetUpRepository.findById(meetId);
////        meet.isPresent();
////        System.out.println(meet.get().getCurrentNumberOfPeople());
////        Assertions.assertThat(meet.get().getAllUsers().get(0).getMeetUpStatus()).isEqualTo("HOST");
//    //질문.. 왜 이렇게 test를 작성하면 null로 print??
//    }
//
//    @Test
//    void participateMeetUp_Success() {
//        // Arrange
//        Long meetUpId = 1L;
//        //Participation participation = new Participation();
//        User user = User.builder()
//                .id(1L) // Set the ID as needed
//                .name("John Doe") // Set the name as needed
//                .authInfo(AuthInfo.builder()
//                        .email("john@example.com") // Set the email as needed
//                        .loginType(LoginType.KAKAO) // Set the login type as needed
//                        .build())
//                .universityName("Example University") // Set the university name as needed
//                .build(); // You may need to create a valid user for testing
//
//        //when(userRepository.save(any())).thenReturn(user);
//        MeetUp meetUp = MeetUp.builder()
//                .id(1L) // Set the ID as needed
//                .name("Sample MeetUp") // Set the name as needed
//                .introduce("A sample meet-up for testing") // Set the introduce as needed
//                .chatRoomLink("https://example.com/chat-room") // Set the chat room link as needed
//                .region("Sample Region") // Set the region as needed
//                .date(LocalDateTime.now()) // Set the date as needed
//                .restaurant("Sample Restaurant") // Set the restaurant as needed
//                .currentNumberOfPeople(0) // Initial current number of people
//                .numberOfPeople(10) // Set the total number of people as needed
//                .meetUpAtmosphere(MeetUpAtmosphere.QUIET) // Set the meet-up atmosphere as needed
//                .regionAtmosphere(RegionAtmosphere.INSTA) // Set the region atmosphere as needed
//                .representationImage("sample-image.jpg") // Set the representation image as needed
//                .build();
//        when(meetUpRepository.findById(meetUpId)).thenReturn(Optional.of(meetUp));
//
//        Participation participation = Participation.builder()
//                .meetUp(meetUp)
//                .user(user)
//                .meetUpStatus(MeetUpStatus.HOST.getStatus())
//                .build();
//        when(participationRepository.save(any())).thenReturn(participation);
//
////        when(meetUpRepository.findById(meetUpId)).thenReturn(Optional.of(meetUp));
////        System.out.println(meetUp.getName());
//        User userA = User.builder()
//                .id(2L) // Set the ID as needed
//                .name("John") // Set the name as needed
//                .authInfo(AuthInfo.builder()
//                        .email("john1@example.com") // Set the email as needed
//                        .loginType(LoginType.KAKAO) // Set the login type as needed
//                        .build())
//                .universityName("Example University") // Set the university name as needed
//                .build(); // You may need to create a valid user for testing
//
//        // Act
//        Participation participationA = meetUpService.participateMeetUp(userA, meetUpId);
//        System.out.println(meetUp.getCurrentNumberOfPeople());
//
//        Assertions.assertThat(participation.getMeetUpStatus()).isEqualTo("HOST");
//        Assertions.assertThat(participationA.getMeetUpStatus()).isEqualTo("PARTICIPANT");
//    }
//
//    @Test
//    void participateMeetUp_Many_Success() {
//        // Arrange
//        Long meetUpId = 1L;
//        //Participation participation = new Participation();
//        User user = User.builder()
//                .id(1L) // Set the ID as needed
//                .name("John Doe") // Set the name as needed
//                .authInfo(AuthInfo.builder()
//                        .email("john@example.com") // Set the email as needed
//                        .loginType(LoginType.KAKAO) // Set the login type as needed
//                        .build())
//                .universityName("Example University") // Set the university name as needed
//                .build(); // You may need to create a valid user for testing
//
//        //when(userRepository.save(any())).thenReturn(user);
//        MeetUp meetUp = MeetUp.builder()
//                .id(1L) // Set the ID as needed
//                .name("Sample MeetUp") // Set the name as needed
//                .introduce("A sample meet-up for testing") // Set the introduce as needed
//                .chatRoomLink("https://example.com/chat-room") // Set the chat room link as needed
//                .region("Sample Region") // Set the region as needed
//                .date(LocalDateTime.now()) // Set the date as needed
//                .restaurant("Sample Restaurant") // Set the restaurant as needed
//                .currentNumberOfPeople(0) // Initial current number of people
//                .numberOfPeople(10) // Set the total number of people as needed
//                .meetUpAtmosphere(MeetUpAtmosphere.QUIET) // Set the meet-up atmosphere as needed
//                .regionAtmosphere(RegionAtmosphere.INSTA) // Set the region atmosphere as needed
//                .representationImage("sample-image.jpg") // Set the representation image as needed
//                .build();
//        when(meetUpRepository.findById(meetUpId)).thenReturn(Optional.of(meetUp));
//
//        Participation participation = Participation.builder()
//                .meetUp(meetUp)
//                .user(user)
//                .meetUpStatus(MeetUpStatus.HOST.getStatus())
//                .build();
//        when(participationRepository.save(any())).thenReturn(participation);
//
////        when(meetUpRepository.findById(meetUpId)).thenReturn(Optional.of(meetUp));
////        System.out.println(meetUp.getName());
//        User userA = User.builder()
//                .id(2L) // Set the ID as needed
//                .name("John") // Set the name as needed
//                .authInfo(AuthInfo.builder()
//                        .email("john1@example.com") // Set the email as needed
//                        .loginType(LoginType.KAKAO) // Set the login type as needed
//                        .build())
//                .universityName("Example University") // Set the university name as needed
//                .build(); // You may need to create a valid user for testing
//        User userB = User.builder()
//                .id(3L) // Set the ID as needed
//                .name("JohnA") // Set the name as needed
//                .authInfo(AuthInfo.builder()
//                        .email("john1A@example.com") // Set the email as needed
//                        .loginType(LoginType.KAKAO) // Set the login type as needed
//                        .build())
//                .universityName("Example University") // Set the university name as needed
//                .build(); // You may need to create a valid user for testing
//
//
//        // Act
//        Participation participationA = meetUpService.participateMeetUp(userA, meetUpId);
//        Participation participationB = meetUpService.participateMeetUp(userB, meetUpId);
//        System.out.println(meetUp.getCurrentNumberOfPeople());
//
//
//        Assertions.assertThat(participation.getMeetUpStatus()).isEqualTo("HOST");
//        Assertions.assertThat(participationA.getMeetUpStatus()).isEqualTo("PARTICIPANT");
//    }
//
//    @Test
//    void participateMeetUp_MeetingNotFound() {
//        // Arrange
//        Long meetUpId = 2L; // Assuming this meet-up does not exist
//        User user = User.builder()
//                .id(1L) // Set the ID as needed
//                .name("John Doe") // Set the name as needed
//                .authInfo(AuthInfo.builder()
//                        .email("john@example.com") // Set the email as needed
//                        .loginType(LoginType.KAKAO) // Set the login type as needed
//                        .build())
//                .universityName("Example University") // Set the university name as needed
//                .build(); // You may need to create a valid user for testing
//
//        // Act and Assert
//        assertThrows(MeetingNotFoundException.class,
//                () -> meetUpService.participateMeetUp(user, meetUpId));
//
//        verify(meetUpRepository, times(1)).findById(meetUpId);
//        verifyNoMoreInteractions(meetUpRepository, participationRepository);
//    }
//
//    @Test
//    void participateMeetUp_FullCapacity() {
//        // Arrange
//        Long meetUpId = 3L;
//        User user = User.builder()
//                .id(1L) // Set the ID as needed
//                .name("John Doe") // Set the name as needed
//                .authInfo(AuthInfo.builder()
//                        .email("john@example.com") // Set the email as needed
//                        .loginType(LoginType.KAKAO) // Set the login type as needed
//                        .build())
//                .universityName("Example University") // Set the university name as needed
//                .build(); // You may need to create a valid user for testing
//
//        //when(userRepository.save(any())).thenReturn(user);
//        MeetUp meetUp = MeetUp.builder()
//                .id(3L) // Set the ID as needed
//                .name("Sample MeetUp") // Set the name as needed
//                .introduce("A sample meet-up for testing") // Set the introduce as needed
//                .chatRoomLink("https://example.com/chat-room") // Set the chat room link as needed
//                .region("Sample Region") // Set the region as needed
//                .date(LocalDateTime.now()) // Set the date as needed
//                .restaurant("Sample Restaurant") // Set the restaurant as needed
//                .currentNumberOfPeople(10) // Initial current number of people
//                .numberOfPeople(10) // Set the total number of people as needed
//                .meetUpAtmosphere(MeetUpAtmosphere.QUIET) // Set the meet-up atmosphere as needed
//                .regionAtmosphere(RegionAtmosphere.INSTA) // Set the region atmosphere as needed
//                .representationImage("sample-image.jpg") // Set the representation image as needed
//                .allUsers(new ArrayList<>()) // Initialize the list of users
//                .build();
//
//        when(meetUpRepository.findById(meetUpId)).thenReturn(Optional.of(meetUp));
//
//        // Act and Assert
//        assertThrows(FullCapacityException.class,
//                () -> meetUpService.participateMeetUp(user, meetUpId));
//
//        verify(meetUpRepository, times(1)).findById(meetUpId);
//        verifyNoMoreInteractions(meetUpRepository, participationRepository);
//    }
}
