package com.traveltrail.backend.controller.Public;

import com.traveltrail.backend.dto.AuthResponceDto;
import com.traveltrail.backend.dto.LoginRequestDto;
import com.traveltrail.backend.dto.RegisterRequestDto;
import com.traveltrail.backend.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
