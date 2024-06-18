package com.traveltrail.backend.service;

import com.traveltrail.backend.dto.FlightDto;
import com.traveltrail.backend.model.tripEntities.Flight;
import com.traveltrail.backend.model.Trip;
import com.traveltrail.backend.repository.FlightRepository;
import com.traveltrail.backend.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightService {
    private final FlightRepository flightRepository;
    private final TripRepository tripRepository;
    private final ModelMapper modelMapper;
    public FlightDto createFlight(FlightDto flightDto){

        Trip trip = tripRepository.findById(flightDto.getTripId()).orElseThrow(
                () -> new IllegalArgumentException("Invalid trip id"));

        Flight flight = Flight.builder()
                .flightNumber(flightDto.getFlightNumber())
                .trip(trip)
                .build();

        flightRepository.save(flight);

        return flightDto;
    }


    public List<FlightDto> getFlightsByTripId(Long tripId){
        List<Flight> flights = flightRepository.findByTripId(tripId);

        return flights.stream().map(flight -> modelMapper.map(flight, FlightDto.class)).toList();
    }

    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }

}
