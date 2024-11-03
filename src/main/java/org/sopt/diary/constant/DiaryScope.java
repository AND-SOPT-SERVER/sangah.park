package org.sopt.diary.constant;

public enum DiaryScope {
    PUBLIC("public"),
    PRIVATE("private");

    private final String value;

    DiaryScope(final String value){
        this.value=value;
    }
}
