package org.sopt.diary.api;

import jakarta.validation.Valid;
import org.sopt.diary.api.dto.req.LoginRequest;
import org.sopt.diary.api.dto.req.SignUpRequest;
import org.sopt.diary.repository.UserEntity;
import org.sopt.diary.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(final UserService userService){
        this.userService = userService;
    }

    @PostMapping("/signup")
    private ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequest signUpRequest){
         UserEntity user = userService.createUser(signUpRequest);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri());
        headers.add("UserId", String.valueOf(user.getId()));

        return new ResponseEntity<> (headers, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    private ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest){
        return userService.findUser(loginRequest);}
}