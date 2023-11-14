package com.bapMate.bapMateServer.domain.meeting.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MeetUpStatus {
    PARTICIPANT("PARTICIPANT"),
    HOST("HOST");

    private final String status;

    public String getStatus() {return status;}
}
