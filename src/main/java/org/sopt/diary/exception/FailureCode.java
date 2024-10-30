package org.sopt.diary.exception;

import org.springframework.http.HttpStatus;

public interface FailureCode {
    HttpStatus getStatus();
    String getMessage();
}
