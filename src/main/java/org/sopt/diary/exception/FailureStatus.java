package org.sopt.diary.exception;

import org.springframework.http.HttpStatus;

public enum FailureStatus implements FailureCode{
    //400 Bad Request 잘못된 요청
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "입력 파라미터를 확인해 주세요."),

    //404 NOT_FOUND 잘못된 리소스 접근
    DIARY_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 다이어리입니다."),

    //405 Method Not Allowed
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "지원하지 않는 HTTP 메소드입니다."),

    //500 Internal Server Error
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류입니다.");

    private final HttpStatus status;
    private final String message;

    FailureStatus(final HttpStatus status, final String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
