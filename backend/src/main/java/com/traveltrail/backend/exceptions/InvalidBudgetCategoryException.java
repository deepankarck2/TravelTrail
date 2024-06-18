package com.traveltrail.backend.exceptions;

public class InvalidBudgetCategoryException extends RuntimeException {
    public InvalidBudgetCategoryException(String message) {
        super(message);
    }
}