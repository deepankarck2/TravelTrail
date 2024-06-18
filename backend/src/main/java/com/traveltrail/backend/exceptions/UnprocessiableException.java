package com.traveltrail.backend.exceptions;

public class UnprocessiableException extends RuntimeException {
    public UnprocessiableException(String message){
        super(message);
    }
}
