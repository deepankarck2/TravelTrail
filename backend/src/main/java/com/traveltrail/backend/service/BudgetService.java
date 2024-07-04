package com.traveltrail.backend.service;

import com.traveltrail.backend.dto.BudgetDto;
import com.traveltrail.backend.model.Trip;
import com.traveltrail.backend.model.budget.Budget;
import com.traveltrail.backend.repository.BudgetRepository;
import com.traveltrail.backend.repository.TripRepository;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Data
@Service
@RequiredArgsConstructor
public class BudgetService {
    private final BudgetRepository budgetRepository;
    private final TripRepository tripRepository;
    private final ModelMapper modelMapper;
    public BudgetDto createBudget(BudgetDto budgetDto) {
        Trip trip = tripRepository.findById(budgetDto.getTripId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid trip ID"));

        Budget budget = Budget.builder()
                .trip(trip)
                .totalBudget(budgetDto.getTotalBudget())
                .currency(budgetDto.getCurrency())
                .build();

        Budget savedBudget = budgetRepository.save(budget);

        return modelMapper.map(savedBudget, BudgetDto.class);
    }


    public BudgetDto getBudgetByTripId(Long tripId) {
        Budget budget = budgetRepository.findByTripId(tripId);
        return modelMapper.map(budget, BudgetDto.class);
    }


}
