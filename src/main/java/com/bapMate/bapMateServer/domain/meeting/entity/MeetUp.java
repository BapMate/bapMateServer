package com.bapMate.bapMateServer.domain.meeting.entity;

import com.bapMate.bapMateServer.domain.meeting.enums.MeetUpAtmosphere;
import com.bapMate.bapMateServer.domain.meeting.enums.RegionAtmosphere;
import com.bapMate.bapMateServer.domain.participation.entity.Participation;
import com.bapMate.bapMateServer.domain.user.entity.User;
import com.bapMate.bapMateServer.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Builder
public class MeetUp extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meeting_id")
    private Long id;
    private String name;
    private String introduce;
    private String chatRoomLink;
    private String region;
    private LocalDateTime date;
    private String restaurant;
    private int currentNumberOfPeople;
    private int numberOfPeople;
    @Enumerated(EnumType.STRING)
    private MeetUpAtmosphere meetUpAtmosphere;
    @Enumerated(EnumType.STRING)
    private RegionAtmosphere regionAtmosphere;
    private String representationImage;

    public int updateNumberOfPeople(int currentNumber) {
        this.currentNumberOfPeople = currentNumber + 1;
        return currentNumberOfPeople;
    }

}
