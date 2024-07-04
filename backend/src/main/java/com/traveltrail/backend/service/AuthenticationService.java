package com.traveltrail.backend.service;

import com.traveltrail.backend.exceptions.UnprocessiableException;
import com.traveltrail.backend.Security.JwtTokenUtil;
import com.traveltrail.backend.dto.AuthResponseDto;
import com.traveltrail.backend.dto.LoginRequestDto;
import com.traveltrail.backend.dto.RegisterRequestDto;
import com.traveltrail.backend.model.User;
import com.traveltrail.backend.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
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
    public AuthResponseDto registerRequestService(RegisterRequestDto requestDto){
        String email = requestDto.getEmail();

        if(userRepository.existsByEmail(email)){
            throw new UnprocessiableException("User already exists!");
        }

        User user = User.builder()
                .email(email)
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .build();

        userRepository.save(user);
        String jwtToken = jwtTokenUtil.generateToken(user);

        return AuthResponseDto.builder()
                .token(jwtToken)
                .build();
    }

    public AuthResponseDto loginRequestService(LoginRequestDto loginRequestDto) {
        if(loginRequestDto == null){
            throw new RuntimeException("loginRequest is null");
        }

        String email = loginRequestDto.getEmail();
        String password = loginRequestDto.getPassword();

        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );
        } catch (BadCredentialsException e) {
                throw new BadCredentialsException("Invalid email or password", e);
        } catch (AuthenticationException e){
            throw new RuntimeException("Authentication failed.", e);
        }


        User user = userRepository.findByEmail(email).orElseThrow();

        String jwtToken = jwtTokenUtil.generateToken(user);

        return AuthResponseDto
                .builder()
                .token(jwtToken)
                .build();
    }
}
