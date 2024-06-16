package com.traveltrail.backend.repository;

import com.traveltrail.backend.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TripRepository extends JpaRepository<Trip, Long> {
    Optional<Trip> findByName(String name);
    List<Trip> findByUserId(Long userId);
}
