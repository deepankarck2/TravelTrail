package com.traveltrail.backend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponseDto {
    private String message;
    private int statusCode;
    private long timestamp;
}
