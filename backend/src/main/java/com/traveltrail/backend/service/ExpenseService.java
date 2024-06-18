package com.traveltrail.backend.service;

import com.traveltrail.backend.dto.ExpenseDto;
import com.traveltrail.backend.model.EntityTypes;
import com.traveltrail.backend.model.budget.BudgetCategory;
import com.traveltrail.backend.model.budget.Expense;
import com.traveltrail.backend.model.Trip;
import com.traveltrail.backend.repository.BudgetCategoryRepository;
import com.traveltrail.backend.repository.ExpenseRepository;
import com.traveltrail.backend.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {
    private final TripRepository tripRepository;
    private final ExpenseRepository expenseRepository;
    private final BudgetCategoryRepository budgetCategoryRepository;
    private final ModelMapper modelMapper;
    public ExpenseDto createExpense(ExpenseDto expenseDto){
        BudgetCategory budgetCategory = budgetCategoryRepository.findByCategoryName(expenseDto.getCategoryName())
                .orElseThrow(() -> new IllegalArgumentException("Invalid budget category name"));

        Trip trip = tripRepository.findById(expenseDto.getTripId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid trip ID"));

        EntityTypes entityType;
        try{
            entityType = EntityTypes.valueOf(expenseDto.getEntityType().toUpperCase());
        }catch (Exception e){
            throw new IllegalArgumentException("Invalid category name: " + expenseDto.getCategoryName());
        }

        Expense expense = Expense.builder()
                .description(expenseDto.getDescription())
                .amount(expenseDto.getAmount())
                .currency(expenseDto.getCurrency())
                .budgetCategory(budgetCategory)
                .trip(trip)
                .expenseDate(expenseDto.getExpenseDate())
                .entityType(entityType)
                .entityId(expenseDto.getEntityId())
                .build();

        return modelMapper.map(expense, ExpenseDto.class);
    }

    public List<ExpenseDto> getExpensesByTripId(Long tripId) {
        List<Expense> expenses = expenseRepository.findByTripId(tripId);

        return expenses.stream().map(expense -> modelMapper.map(expense, ExpenseDto.class)).toList();
    }

    public List<ExpenseDto> getExpensesByBudgetCategoryId(Long budgetCategoryId) {
        List<Expense> expenses = expenseRepository.findByBudgetCategoryId(budgetCategoryId);

        return expenses.stream().map(expense -> modelMapper.map(expense, ExpenseDto.class)).toList();
    }

    public BigDecimal getTotalExpenseByTripId(Long tripId) {
        boolean tripExists = tripRepository.existsById(tripId);
        if(!tripExists){
            throw new IllegalArgumentException("Invalid trip id");
        }

        List<Expense> expenses = expenseRepository.findByTripId(tripId);

        BigDecimal totalExpense = expenses.stream()
                .map(expense -> expense.getAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return totalExpense;
    }
}
