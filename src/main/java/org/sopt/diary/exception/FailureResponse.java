package org.sopt.diary.exception;

import java.util.HashMap;
import java.util.Map;

public record FailureResponse(
        int code,
        String message,
        Map<String, Object> data
) {
    public static FailureResponse of(FailureCode failureCode) {
        return new FailureResponse(failureCode.getCode(), failureCode.getMessage(), new HashMap<>());
    }
}
