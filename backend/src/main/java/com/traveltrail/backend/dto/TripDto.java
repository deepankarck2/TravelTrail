package com.traveltrail.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TripDto {
    private String name;
    private String destination;
    private LocalDate startDate;
    private LocalDate endDate;
    private String coverPhotoUrl;
    private long userId;
}
