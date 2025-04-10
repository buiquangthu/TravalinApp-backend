package com.bqt.flight_booking_server.dto.response;

import lombok.Data;

@Data
public class AirportResponse {
    private String code;
    private String name;
    private String location;
}