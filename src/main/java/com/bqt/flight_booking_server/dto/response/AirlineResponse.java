package com.bqt.flight_booking_server.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AirlineResponse {
    private String airlineId;
    private String airName;
    private String contactNumber;
    private String urlUrl;
}
