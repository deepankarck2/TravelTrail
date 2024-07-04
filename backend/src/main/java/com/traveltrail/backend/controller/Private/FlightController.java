package com.traveltrail.backend.controller.Private;

import com.traveltrail.backend.dto.FlightDto;
import com.traveltrail.backend.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
@RequiredArgsConstructor
public class FlightController {
    private final FlightService flightService;

    @PostMapping("/create")
    public ResponseEntity<FlightDto> createFlight(@RequestBody FlightDto flightDto){

        return ResponseEntity.status(HttpStatus.CREATED).body(flightService.createFlight(flightDto));
    }

    @GetMapping("/{tripId}")
    public ResponseEntity<List<FlightDto>> getFlightsByTripId(@PathVariable Long tripId){
        return ResponseEntity.ok(flightService.getFlightsByTripId(tripId));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
        return ResponseEntity. noContent().build();
    }

}
