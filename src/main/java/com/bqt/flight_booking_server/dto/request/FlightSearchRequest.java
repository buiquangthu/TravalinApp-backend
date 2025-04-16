package com.bqt.flight_booking_server.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FlightSearchRequest {
    private String originAirportCode;
    private String destinationAirportCode;
    private LocalDate departureDate;
}