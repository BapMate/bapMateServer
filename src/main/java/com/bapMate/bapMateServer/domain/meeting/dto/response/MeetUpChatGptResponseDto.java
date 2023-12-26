package com.bapMate.bapMateServer.domain.meeting.dto.response;

import com.bapMate.bapMateServer.domain.meeting.entity.MeetUp;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class MeetUpChatGptResponseDto {
    private List<MeetUpResponseDto> meetUpList;
    private String message;

    public MeetUpChatGptResponseDto(List<MeetUpResponseDto> meetUpList, String message) {
        this.meetUpList = meetUpList;
        this.message = message;
    }

    public List<MeetUpResponseDto> getMeetUpList() {
        return meetUpList;
    }

    public String getMessage() {
        return message;
    }
}

