package com.bapMate.bapMateServer.domain.meeting.entity;

import com.bapMate.bapMateServer.domain.meeting.enums.MeetUpAtmosphere;
import com.bapMate.bapMateServer.domain.meeting.enums.RegionAtmosphere;
import com.bapMate.bapMateServer.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Builder
public class MeetUp extends BaseTimeEntity {
    @Id
    @GeneratedValue
    @Column(name = "meeting_id")
    private Long id;

    private String name;
    private String introduce;
    private String chatRoomLink;
    private String region;
    private LocalDateTime date;
    private String restaurant;
    private int numberOfPeople;
    @Enumerated(EnumType.STRING)
    private MeetUpAtmosphere meetUpAtmosphere;
    @Enumerated(EnumType.STRING)
    private RegionAtmosphere regionAtmosphere;
    private String representationImage;

}
