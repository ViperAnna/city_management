package com.example.city_management.exception;

public class PassportNotFoundException extends RuntimeException {
    public PassportNotFoundException(String message) {
        super(message);
    }
}
