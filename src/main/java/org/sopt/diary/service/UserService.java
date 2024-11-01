package org.sopt.diary.service;

import jakarta.validation.Valid;
import org.sopt.diary.api.dto.req.SignUpRequest;
import org.sopt.diary.repository.UserEntity;
import org.sopt.diary.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(@Valid SignUpRequest signUpRequest){
        userRepository.save(new UserEntity(signUpRequest.name(), signUpRequest.nickname().replaceAll(" ", ""), signUpRequest.email(), signUpRequest.password()));
    }
}

