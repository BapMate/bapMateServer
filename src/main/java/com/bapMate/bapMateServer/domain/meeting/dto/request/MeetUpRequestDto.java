package com.bapMate.bapMateServer.domain.meeting.dto.request;

import com.bapMate.bapMateServer.domain.meeting.entity.MeetUp;
import com.bapMate.bapMateServer.domain.meeting.enums.MeetUpAtmosphere;
import com.bapMate.bapMateServer.domain.meeting.enums.RegionAtmosphere;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
public class MeetUpRequestDto {
    private String name;
    private String introduce;
    private String chatRoomLink;
    private String region;
    private LocalDateTime date;
    private String restaurant;
    private int numberOfPeople;
    //private MeetUpAtmosphere meetUpAtmosphere;
    //private RegionAtmosphere regionAtmosphere;
    private String representationImage;

    public MeetUp toEntity() {
        return MeetUp.builder()
                .chatRoomLink(chatRoomLink)
                .date(date)
                //.meetUpAtmosphere(meetUpAtmosphere)
                .introduce(introduce)
                .restaurant(restaurant)
                .numberOfPeople(numberOfPeople)
                .region(region)
                //.regionAtmosphere(regionAtmosphere)
                .representationImage(representationImage)
                .name(name)
                .build();

    }
}
