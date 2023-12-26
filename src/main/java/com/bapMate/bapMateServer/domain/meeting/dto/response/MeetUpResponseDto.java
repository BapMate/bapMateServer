package com.bapMate.bapMateServer.domain.meeting.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
public class MeetUpResponseDto {
    private Long id;
    private String name;
    private String introduce;
    private String chatRoomLink;
    private String region;
    private LocalDateTime date;
    private String restaurant;
    private int numberOfPeople;
    private int currentNumberOfPeople;
    private String meetUpAtmosphere;
    private String regionAtmosphere;
    private String representationImage;
}
