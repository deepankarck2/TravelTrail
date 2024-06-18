package com.traveltrail.backend.controller.Private;

import com.traveltrail.backend.dto.BudgetCategoryDto;
import com.traveltrail.backend.dto.BudgetDto;
import com.traveltrail.backend.service.BudgetCategoryService;
import com.traveltrail.backend.service.BudgetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budgets")
@RequiredArgsConstructor
public class BudgetController {
    private final BudgetService budgetService;
    private final BudgetCategoryService budgetCategoryService;


    @PostMapping("/create")
    public ResponseEntity<BudgetDto> createBudget(@RequestBody BudgetDto budgetDto) {
        return ResponseEntity.ok(budgetService.createBudget(budgetDto));
    }

    @GetMapping("/trip/{tripId}")
    public ResponseEntity<BudgetDto> getBudgetByTripId(@PathVariable Long tripId) {
        return ResponseEntity.ok(budgetService.getBudgetByTripId(tripId));
    }

    @PostMapping("/categories/create")
    public ResponseEntity<BudgetCategoryDto> createBudgetCategory(@RequestBody BudgetCategoryDto budgetCategoryDto) {
        return ResponseEntity.status(HttpStatus.CREATED).
                body(budgetCategoryService.createBudgetCategory(budgetCategoryDto));
    }

    @GetMapping("/categories/{budgetId}")
    public ResponseEntity<List<BudgetCategoryDto>> getBudgetCategoriesByBudgetId(@PathVariable Long budgetId) {
        return ResponseEntity.ok(budgetCategoryService.getBudgetCategoriesByBudgetId(budgetId));
    }
}