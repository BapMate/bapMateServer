package com.bapMate.bapMateServer.domain.user.exception;

import com.bapMate.bapMateServer.domain.meeting.exception.MeetingErrorCode;
import com.bapMate.bapMateServer.global.exception.base.BaseException;

public class UniversityNotAuthenticated extends BaseException {
    public static final BaseException EXCEPTION = new UniversityNotAuthenticated();
    public UniversityNotAuthenticated() {
        super(UserErrorCode.NO_UNIVERSITY_AUTHENTICATION);
    }
}
