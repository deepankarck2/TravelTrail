package com.traveltrail.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class FlightDto {
    private String flightNumber;
    private String flightConfirmationNumber;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private String airline;
    private String departureAirport;
    private String arrivalAirport;
    private long tripId;
}
