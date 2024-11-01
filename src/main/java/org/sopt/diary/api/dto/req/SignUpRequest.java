package org.sopt.diary.api.dto.req;

public record SignUpRequest (
        String name,
        String nickname, String email, String password){
}

