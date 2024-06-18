package com.traveltrail.backend.service;

import com.traveltrail.backend.dto.BudgetCategoryDto;
import com.traveltrail.backend.exceptions.InvalidBudgetCategoryException;
import com.traveltrail.backend.model.budget.Budget;
import com.traveltrail.backend.model.budget.BudgetCategory;
import com.traveltrail.backend.model.budget.BudgetCategoryType;
import com.traveltrail.backend.repository.BudgetCategoryRepository;
import com.traveltrail.backend.repository.BudgetRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Data
@Service
@RequiredArgsConstructor
public class BudgetCategoryService {
    private final BudgetRepository budgetRepository;
    private final BudgetCategoryRepository budgetCategoryRepository;
    private final ModelMapper modelMapper;

    public BudgetCategoryDto createBudgetCategory(BudgetCategoryDto budgetCategoryDto) {
        long budgetId = budgetCategoryDto.getBudgetId();
        BigDecimal allocatedAmountForBudgetCategory = budgetCategoryDto.getAllocatedAmount();

        Budget budget = budgetRepository.findById(budgetId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid budget id"));

        BigDecimal totalAllocatedAmount  = budgetCategoryRepository.findByBudgetId(budgetId).stream()
                .map(BudgetCategory::getAllocatedAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalBudget = budget.getTotalBudget();
        if(totalAllocatedAmount.add(allocatedAmountForBudgetCategory).compareTo(totalBudget) > 0){
            throw new IllegalArgumentException("Total allocated amount exceeds the total budget");
        }

        BudgetCategoryType categoryType;
        try {
            categoryType = BudgetCategoryType.valueOf(budgetCategoryDto.getCategoryName().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidBudgetCategoryException("Invalid category name: " + budgetCategoryDto.getCategoryName());
        }

        BudgetCategory budgetCategory = BudgetCategory.builder()
                .budget(budget)
                .categoryName(categoryType)
                .allocatedAmount(allocatedAmountForBudgetCategory)
                .spentAmount(budgetCategoryDto.getSpentAmount())
                .build();

        BudgetCategory savedBudgetCategory = budgetCategoryRepository.save(budgetCategory);

        return modelMapper.map(savedBudgetCategory, BudgetCategoryDto.class);
    }


    public List<BudgetCategoryDto> getBudgetCategoriesByBudgetId(Long budgetId) {
        boolean budgetExists = budgetRepository.existsById(budgetId);
        if(!budgetExists){
            throw new IllegalArgumentException("Invalid Budget id");
        }

        List<BudgetCategory> budgetCategories = budgetCategoryRepository.findByBudgetId(budgetId);

        List<BudgetCategoryDto> budgetCategoryDtos = budgetCategories.stream()
                .map(budgetCategory ->
                modelMapper.map(budgetCategory, BudgetCategoryDto.class)).toList();

        return budgetCategoryDtos;
    }
}
