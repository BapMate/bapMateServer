package com.bapMate.bapMateServer.domain.meeting.exception;

import com.bapMate.bapMateServer.global.exception.base.BaseException;

public class MeetingNotFoundException extends BaseException {
    public MeetingNotFoundException() {
        super(MeetingErrorCode.MEETING_NOT_FOUND);
    }
}
