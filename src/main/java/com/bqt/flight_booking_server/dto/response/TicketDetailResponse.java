package com.bqt.flight_booking_server.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TicketDetailResponse {
    private Long bookingId;
    private String flightNumber;
    private String airline;
    private String departureTime;
    private String arrivalTime;
    private String origin;
    private String destination;
    private String seatNumber;
    private String passengerName;
    private String passengerPhone;
    private String status;
    private Double price;
}
