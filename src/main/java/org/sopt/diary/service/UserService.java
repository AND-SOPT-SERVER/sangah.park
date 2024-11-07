package org.sopt.diary.service;

import jakarta.validation.Valid;
import org.sopt.diary.api.dto.req.LoginRequest;
import org.sopt.diary.api.dto.req.SignUpRequest;
import org.sopt.diary.exception.FailureResponse;
import org.sopt.diary.exception.FailureStatus;
import org.sopt.diary.repository.UserEntity;
import org.sopt.diary.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity createUser(@Valid SignUpRequest signUpRequest){
        UserEntity user = (new UserEntity(signUpRequest.name(), signUpRequest.nickname().replaceAll(" ", ""), signUpRequest.email(), signUpRequest.password()));
        return userRepository.save(user);
    }

    public ResponseEntity<?> findUser(@Valid LoginRequest loginRequest) {
        UserEntity user = userRepository.findByEmail(loginRequest.email());
        if (user == null) {
            FailureStatus failureStatus = FailureStatus.INVALID_EMAIL;
            return ResponseEntity.status(400).body(FailureResponse.of(failureStatus));
        } else{
            if(!Objects.equals(user.getPassword(), loginRequest.password())){
                FailureStatus failureStatus = FailureStatus.UNAUTHORIZED;
                return ResponseEntity.status(401).body(FailureResponse.of(failureStatus));
            } else{
                return ResponseEntity.ok(user);}
        }
    }
}

