package com.traveltrail.backend.repository;

import com.traveltrail.backend.model.budget.BudgetCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BudgetCategoryRepository extends JpaRepository<BudgetCategory, Long> {
    Optional<BudgetCategory> findByCategoryName(String categoryName);
    List<BudgetCategory> findByBudgetId(Long budgetId);

}
