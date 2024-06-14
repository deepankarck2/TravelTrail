package com.traveltrail.backend.model;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    private String destination;
    private Date startDate;
    private Date endDate;
    private String coverPhotoUrl;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Flight> flightList;
}
