package com.traveltrail.backend.repository;

import com.traveltrail.backend.model.tripEntities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByTripId(Long tripId);
    void deleteById(long id);
}
