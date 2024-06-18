package com.traveltrail.backend.repository;


import com.traveltrail.backend.model.budget.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByTripId(Long tripId);

    List<Expense> findByBudgetCategoryId(Long budgetCategoryId);
}