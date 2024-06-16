package com.traveltrail.backend.Exceptions;

public class UnprocessiableException extends RuntimeException {
    public UnprocessiableException(String message){
        super(message);
    }
}
