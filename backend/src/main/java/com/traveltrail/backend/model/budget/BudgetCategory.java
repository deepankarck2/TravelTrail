package com.traveltrail.backend.model.budget;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Builder
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class BudgetCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BudgetCategoryType categoryName;

    @Column(nullable = false)
    private BigDecimal allocatedAmount;

    @Column(nullable = false)
    private BigDecimal spentAmount;


    @ManyToOne
    @JoinColumn(name = "budget_id", referencedColumnName = "id", nullable = false)
    private Budget budget;

    @OneToMany(mappedBy = "budgetCategory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Expense> expense;
}
