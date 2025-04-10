package com.bqt.flight_booking_server.entity;

public enum FlightStatus {
    OPEN("Đang mở bán"),
    CLOSED("Đã đóng"),
    DELAYED("Hoãn");

    private final String description;

    FlightStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static FlightStatus fromValue(String value) {
        for (FlightStatus status : values()) {
            if (status.getDescription().equalsIgnoreCase(value.trim())) {
                return status;
            }
        }
        throw new IllegalArgumentException("No enum constant with description: " + value);
    }
}