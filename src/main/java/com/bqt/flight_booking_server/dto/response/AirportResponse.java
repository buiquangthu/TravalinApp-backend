package com.bqt.flight_booking_server.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AirportResponse {
    private String airport_code;
    private String airport_name;
    private String location;
}