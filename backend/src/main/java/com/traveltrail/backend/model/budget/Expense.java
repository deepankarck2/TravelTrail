package com.traveltrail.backend.model.budget;

import com.traveltrail.backend.model.EntityTypes;
import com.traveltrail.backend.model.Trip;
import com.traveltrail.backend.model.budget.BudgetCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private BigDecimal amount;
    private String currency;
    private LocalDateTime expenseDate;

    @ManyToOne
    @JoinColumn(name = "trip_id", referencedColumnName = "id", nullable = false)
    private Trip trip;

    @ManyToOne
    @JoinColumn(name = "budget_category_id", referencedColumnName = "id")
    private BudgetCategory budgetCategory;

    @Column(name = "entity_type")
    @Enumerated(EnumType.STRING)
    private EntityTypes entityType;

    private Long entityId; // ID of the related entity (e.g., flight, hotel)

}
