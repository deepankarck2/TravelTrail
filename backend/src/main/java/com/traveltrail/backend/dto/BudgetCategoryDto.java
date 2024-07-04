package com.traveltrail.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BudgetCategoryDto {
    private long id;
    private long budgetId;
    private String categoryName;
    private BigDecimal allocatedAmount;
    private BigDecimal spentAmount;
}