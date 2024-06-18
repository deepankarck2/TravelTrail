package com.traveltrail.backend.controller.Private;

import com.traveltrail.backend.dto.ExpenseDto;
import com.traveltrail.backend.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;
    @PostMapping("/create")
    public ResponseEntity<ExpenseDto> createExpense(@RequestBody ExpenseDto expenseDto) {
        return ResponseEntity.ok(expenseService.createExpense(expenseDto));
    }

    @GetMapping("/trip/{tripId}")
    public ResponseEntity<List<ExpenseDto>> getExpensesByTripId(@PathVariable Long tripId) {
        return ResponseEntity.ok(expenseService.getExpensesByTripId(tripId));
    }

    @GetMapping("/category/{budgetCategoryId}")
    public ResponseEntity<List<ExpenseDto>> getExpensesByBudgetCategoryId(@PathVariable Long budgetCategoryId) {
        return ResponseEntity.ok(expenseService.getExpensesByBudgetCategoryId(budgetCategoryId));
    }

    @GetMapping("/totalExpenses")
    public ResponseEntity<BigDecimal> getTotalExpenseByTripId(Long tripId){
        return ResponseEntity.ok(expenseService.getTotalExpenseByTripId(tripId));
    }
}
