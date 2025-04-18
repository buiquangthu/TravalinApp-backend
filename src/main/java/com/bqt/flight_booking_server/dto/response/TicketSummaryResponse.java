package com.bqt.flight_booking_server.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TicketSummaryResponse {
    private String id; // BookingId dưới dạng String

    private FlightInfo flight;

    private String code; // flightNumber
    private Double price;
    private String status;
    private String date;

    @Data
    @Builder
    public static class FlightInfo {
        private String from;
        private String to;
        private String departure;
        private String arrival;
    }
}