package com.bqt.flight_booking_server.dto.request;

import com.bqt.flight_booking_server.entity.FlightClass;
import com.bqt.flight_booking_server.entity.FlightStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class FlightRequest {
    private String flightNumber;
    private String airlineCode;
    private String originAirportCode;
    private String destinationAirportCode;
    private LocalDateTime departureDatetime;
    private LocalDateTime arrivalDatetime;
    private Integer availableSeats;
    private FlightStatus status;
    private Integer duration;
    private Double price;
    private Integer stops;
    private FlightClass flightClass;
}
