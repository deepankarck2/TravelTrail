package com.traveltrail.backend.controller;

import com.traveltrail.backend.Exceptions.UnprocessiableException;
import com.traveltrail.backend.dto.AuthResponceDto;
import com.traveltrail.backend.dto.ErrorResponseDto;
import com.traveltrail.backend.dto.LoginRequestDto;
import com.traveltrail.backend.dto.RegisterRequestDto;
import com.traveltrail.backend.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("/api/public")

public class PublicController {
    private final AuthenticationService authenticationService;
    public PublicController(AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }

    @GetMapping("")
    public String defaultMsg(){
        return "Hello form TravelTrail Backend";
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponceDto> register(@RequestBody RegisterRequestDto registerRequestDto){
        return ResponseEntity.ok(authenticationService.registerRequestService(registerRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponceDto> login
            (@RequestBody LoginRequestDto loginRequestDto){

            AuthResponceDto response = (authenticationService.loginRequestService(loginRequestDto));
            return ResponseEntity.ok(response);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponseDto> handleBadCredentialsException(RuntimeException ex, WebRequest request) {
        ErrorResponseDto errorResponse = ErrorResponseDto.builder()
                .message(ex.getMessage())
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .timestamp(System.currentTimeMillis())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDto> handleRuntimeException(RuntimeException ex, WebRequest request) {

        ErrorResponseDto errorResponse = ErrorResponseDto.builder()
                .message(ex.getMessage())
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timestamp(System.currentTimeMillis())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UnprocessiableException.class)
    public ResponseEntity<ErrorResponseDto> handleUnprocessibleException(Exception ex){
        ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
                .message(ex.getMessage())
                .statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .timestamp(System.currentTimeMillis()).build();

        return new ResponseEntity<>(errorResponseDto, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
