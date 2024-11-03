package org.sopt.diary.exception;

import org.springframework.http.HttpStatus;

public enum FailureStatus implements FailureCode{

    //400 Bad Request 잘못된 요청
    INVALID_PARAMETER(400, "입력 파라미터를 확인해 주세요."),
    INVALID_EMAIL(40000, "이메일 형식이 유효하지 않습니다."),
    INVALID_NAME_NICKNAME_LENGTH(40001, "글자수는 최대 10자까지 입력 가능합니다."),

    //401 Unauthorized
    UNAUTHORIZED(40100, "비밀번호가 잘못되었습니다."),

    //404 NOT_FOUND 잘못된 리소스 접근
    DIARY_NOT_FOUND(40400, "존재하지 않는 다이어리입니다."),
    USER_NOT_FOUND(40400, "존재하지 않는 사용자입니다."),

    //405 Method Not Allowed
    METHOD_NOT_ALLOWED(40500, "지원하지 않는 HTTP 메소드입니다."),

    //409 CONFLICT
    CONFLICT_EMAIL(40900, "이메일 중복입니다."),
    CONFLICT_NICKNAME(40901, "닉네임 중복입니다."),

    //500 Internal Server Error
    INTERNAL_ERROR(50000, "서버 내부 오류입니다.");

    private final int code;
    private final String message;

    FailureStatus(final int code, final String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
