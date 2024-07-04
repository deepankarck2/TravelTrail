package com.traveltrail.backend.controller.Public;

import com.traveltrail.backend.dto.AuthResponseDto;
import com.traveltrail.backend.dto.LoginRequestDto;
import com.traveltrail.backend.dto.RegisterRequestDto;
import com.traveltrail.backend.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public")
@CrossOrigin(origins = "http://localhost:3000")
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
    public ResponseEntity<AuthResponseDto> register(@RequestBody RegisterRequestDto registerRequestDto){
        return ResponseEntity.ok(authenticationService.registerRequestService(registerRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login
            (@RequestBody LoginRequestDto loginRequestDto){

            AuthResponseDto response = (authenticationService.loginRequestService(loginRequestDto));
            return ResponseEntity.ok(response);
    }

}
