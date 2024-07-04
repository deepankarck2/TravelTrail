package com.traveltrail.backend.model.budget;

import com.traveltrail.backend.model.Trip;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Budget {
    //    tripId (Foreign Key)
    //    totalBudget (Decimal)
    //    currency (String)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private BigDecimal totalBudget;

    private String currency;

    @OneToOne
    @JoinColumn(name = "trip_id", referencedColumnName = "id")
    private Trip trip;
}
