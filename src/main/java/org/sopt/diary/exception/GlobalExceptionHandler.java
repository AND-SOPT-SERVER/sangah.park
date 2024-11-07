package org.sopt.diary.exception;

import org.springframework.http.ResponseEntity;
import jakarta.validation.ConstraintViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(GlobalException.class)
    protected ResponseEntity<FailureResponse> handleGlobalException(final GlobalException e){
        return ResponseEntity.status(e.getFailureCode().getCode())
                .body(FailureResponse.of(e.getFailureCode()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<FailureResponse> hanldeConstraintViolationException(final ConstraintViolationException e){
        FailureStatus failureStatus = FailureStatus.INVALID_PARAMETER;

        for(var violation : e.getConstraintViolations()){
            String propertyPath = violation.getPropertyPath().toString();

            if("nickname".equals(propertyPath) || "name".equals(propertyPath)){
                failureStatus = FailureStatus.INVALID_NAME_NICKNAME_LENGTH ;
                break;
            } else if ("email".equals(propertyPath)){
                failureStatus = FailureStatus.INVALID_EMAIL ;
                break;
            }
        }
        return ResponseEntity.badRequest().body(FailureResponse.of(failureStatus));
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    protected ResponseEntity<FailureResponse> handleSQLIntegrityConstraintViolationException(final SQLIntegrityConstraintViolationException e){
        FailureStatus failureStatus = FailureStatus.CONFLICT_EMAIL;
        return ResponseEntity.status(409)
                .body(FailureResponse.of(failureStatus));
    }
}
