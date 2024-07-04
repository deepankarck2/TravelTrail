package com.traveltrail.backend.repository;

import com.traveltrail.backend.model.budget.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
    Budget findByTripId(Long tripId);
    boolean existsById(long id);
}
