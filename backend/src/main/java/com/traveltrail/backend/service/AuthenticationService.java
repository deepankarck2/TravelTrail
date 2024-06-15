package com.traveltrail.backend.service;

import com.traveltrail.backend.Security.JwtTokenUtil;
import com.traveltrail.backend.dto.AuthResponceDto;
import com.traveltrail.backend.dto.RegisterRequestDto;
import com.traveltrail.backend.model.User;
import com.traveltrail.backend.repository.UserRepository;
import io.jsonwebtoken.impl.JwtTokenizer;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
@Service

public class AuthenticationService {
    public final UserRepository userRepository;
    public final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenUtil jwtTokenUtil, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
        this.authenticationManager = authenticationManager;
    }
    public AuthResponceDto registerRequestService(RegisterRequestDto requestDto){
        String email = requestDto.getEmail();

        if(userRepository.existsByEmail(email)){
            throw new RuntimeException("User already exists!");
        }

        User user = User.builder()
                .email(email)
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .build();

        userRepository.save(user);
        String jwtToken = jwtTokenUtil.generateToken(user);

        return AuthResponceDto.builder()
                .token(jwtToken)
                .build();
    }
}
