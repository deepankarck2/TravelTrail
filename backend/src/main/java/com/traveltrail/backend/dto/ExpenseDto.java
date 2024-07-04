package com.traveltrail.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseDto {
    private String description;
    private BigDecimal amount;
    private String currency;
    private String categoryName;
    private long tripId;
    private LocalDateTime expenseDate;
    private String entityType;
    private Long entityId;
}