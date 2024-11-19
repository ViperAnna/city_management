package com.example.city_management.exception;

public class HouseNotFoundException extends RuntimeException {
    public HouseNotFoundException(String message) {
        super(message);
    }
}
