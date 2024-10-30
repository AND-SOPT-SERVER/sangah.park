package org.sopt.diary.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(GlobalException.class)
    protected ResponseEntity<FailureResponse> handleException(final GlobalException e){
        return ResponseEntity.status(e.getFailureCode().getStatus().value()).body(FailureResponse.of(e.getFailureCode()));
    }
}
