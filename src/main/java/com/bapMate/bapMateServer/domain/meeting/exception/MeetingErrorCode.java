package com.bapMate.bapMateServer.domain.meeting.exception;

import com.bapMate.bapMateServer.global.exception.base.BaseErrorCode;
import com.bapMate.bapMateServer.global.exception.dto.ErrorReason;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.bapMate.bapMateServer.global.constant.StaticValue.*;

@Getter
@AllArgsConstructor
public enum MeetingErrorCode implements BaseErrorCode {
    FULL_CAPACITY_ERROR(BAD_REQUEST,"MEETING_404","인원이 다 찼습니다"),
    MEETING_NOT_FOUND(BAD_REQUEST,"MEETING_404","모임이 존재하지 않습니다");

    private final int status;
    private final String code;
    private final String reason;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status, code, reason);
    }
}
