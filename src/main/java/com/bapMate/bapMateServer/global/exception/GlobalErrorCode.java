package com.bapMate.bapMateServer.global.exception;

import com.bapMate.bapMateServer.global.exception.base.BaseErrorCode;
import com.bapMate.bapMateServer.global.exception.dto.ErrorReason;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.bapMate.bapMateServer.global.constant.StaticValue.*;

@Getter
@AllArgsConstructor
public enum GlobalErrorCode implements BaseErrorCode {
    /** example * */
    EXAMPLE_ERROR(BAD_REQUEST, "GLOBAL_400_0", "에러 예시 입니다."),

    /** Server 오류 * */
    HTTP_MESSAGE_NOT_READABLE(BAD_REQUEST, "GLOBAL_400_1", "잘못된 형식의 값을 입력했습니다."),
    NO_APPLE_CODE(BAD_REQUEST, "GLOBAL_400_2", "애플 코드가 필요합니다."),
    EMPTY_PARAM_VALUE(BAD_REQUEST, "GLOBAL_400_3", "빈 파람 값을 입력했습니다.");

    private int status;
    private String code;
    private String reason;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status, code, reason);
    }
}
