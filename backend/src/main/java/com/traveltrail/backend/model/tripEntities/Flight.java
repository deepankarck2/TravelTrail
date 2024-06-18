package com.traveltrail.backend.model.tripEntities;

import com.traveltrail.backend.model.Trip;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String flightNumber;
    private String flightConfirmationNumber;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private String airline;
    private String departureAirport;
    private String arrivalAirport;

    @ManyToOne
    @JoinColumn(name = "trip_id", referencedColumnName = "id", nullable = false)
    private Trip trip;
}
