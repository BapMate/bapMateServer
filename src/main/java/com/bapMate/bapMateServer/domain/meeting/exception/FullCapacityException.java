package com.bapMate.bapMateServer.domain.meeting.exception;

import com.bapMate.bapMateServer.global.exception.base.BaseException;

public class FullCapacityException extends BaseException {
    public FullCapacityException() {
        super(MeetingErrorCode.FULL_CAPACITY_ERROR);
    }
}
