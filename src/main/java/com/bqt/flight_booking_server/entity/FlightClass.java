package com.bqt.flight_booking_server.entity;

public enum FlightClass {
    ECONOMY("Economy"),
    BUSINESS("Business"),
    FIRST("First");

    private final String description;

    FlightClass(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}