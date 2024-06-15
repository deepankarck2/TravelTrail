package com.traveltrail.backend.controller;

import com.traveltrail.backend.dto.RegisterRequestDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public")
public class PublicController {
    @GetMapping("")
    public String defaultMsg(){
        return "Hello form TravelTrail Backend";
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequestDto registerRequestDto){
        return "";
    }
}
