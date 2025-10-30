package com.tripease.exceptions;

public class DriverNotFoundException extends RuntimeException {
    public DriverNotFoundException(String message) {
        super(message);
    }
}
