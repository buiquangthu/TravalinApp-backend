package com.bqt.flight_booking_server.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Builder
@Getter
@Setter
@AllArgsConstructor
public class JwtResponse {
    private String accessToken;
//    private String refreshToken;
}
