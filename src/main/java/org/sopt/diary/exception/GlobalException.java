package org.sopt.diary.exception;

public class GlobalException extends RuntimeException {
    private FailureCode failureCode;

    public GlobalException(final FailureCode failureCode){
        super(failureCode.getMessage());
        this.failureCode=failureCode;
    }

    public FailureCode getFailureCode() {
        return failureCode;
    }
}
