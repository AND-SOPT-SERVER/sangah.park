package org.sopt.diary.api;

import jakarta.validation.Valid;
import org.sopt.diary.api.dto.req.SignUpRequest;
import org.sopt.diary.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(final UserService userService){
        this.userService = userService;
    }

    @PostMapping("/signup")
    private ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequest signUpRequest){
        userService.createUser(signUpRequest);
        return ResponseEntity.ok().build();
    }
}