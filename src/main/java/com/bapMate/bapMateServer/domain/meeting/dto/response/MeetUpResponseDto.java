package com.bapMate.bapMateServer.domain.meeting.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
public class MeetUpResponseDto {
    private Long id;
    private String name;
    private String introduce;
    private String chatRoomLink;
    private String region;
    private LocalDateTime date;
    private String restaurant;
    private int numberOfPeople;
    private String meetUpAtmosphere;
    private String regionAtmosphere;
    private String representationImage;
}
