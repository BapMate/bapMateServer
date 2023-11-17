package com.bapMate.bapMateServer.domain.meeting.exception;

import com.bapMate.bapMateServer.global.exception.base.BaseException;

public class NotAllowedToParticipateException  extends BaseException {
    public NotAllowedToParticipateException() {super(MeetingErrorCode.NOT_ALLOWED_TO_PARTICIPATE_ERROR);}
}
