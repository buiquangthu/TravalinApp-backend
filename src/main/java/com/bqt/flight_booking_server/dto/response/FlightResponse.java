package com.bqt.flight_booking_server.dto.response;

import com.bqt.flight_booking_server.entity.FlightClass;
import com.bqt.flight_booking_server.entity.FlightStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class FlightResponse {

    private Long flightId;
    private String flightNumber;
    private String airlineCode;
    private String airlineName;
    private String originAirportCode;
    private String originAirportName;
    private String destinationAirportCode;
    private String destinationAirportName;
    private LocalDateTime departureDatetime;
    private LocalDateTime arrivalDatetime;
    private Integer availableSeats;
    private FlightStatus status;
    private Integer duration;
    private Double price;
    private Integer stops;
    private FlightClass flightClass;
}
