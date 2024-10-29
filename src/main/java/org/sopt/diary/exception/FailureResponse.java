package org.sopt.diary.exception;

public record FailureResponse(
        int status,
        String message
) {
    public static FailureResponse of(FailureCode failureCode) {
        return new FailureResponse(failureCode.getStatus().value(), failureCode.getMessage());
    }
}
