package com.bapMate.bapMateServer.domain.meeting.exception;

import com.bapMate.bapMateServer.global.exception.base.BaseException;

public class AlreadyParticipatedException extends BaseException {
    public AlreadyParticipatedException() {super(MeetingErrorCode.ALREADY_PARTICIPATED_ERROR);}
}
