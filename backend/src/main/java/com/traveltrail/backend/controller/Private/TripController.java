package com.traveltrail.backend.controller.Private;

import com.traveltrail.backend.dto.ErrorResponseDto;
import com.traveltrail.backend.dto.TripDto;
import com.traveltrail.backend.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trips")
@RequiredArgsConstructor
public class TripController {
    private final TripService tripService;

    @PostMapping("/create")
    public ResponseEntity<TripDto> createTrip(@RequestBody TripDto trip){

        return ResponseEntity.ok(tripService.createTrip(trip));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<TripDto>> getTripByUser(@PathVariable Long userId){

        return ResponseEntity.ok(tripService.findTripsByUserid(userId));
    }

    @PostMapping("/delete/{tripId}")
    public ResponseEntity<TripDto> deleteTrip(@PathVariable Long tripId){

        return null;
    }
}
