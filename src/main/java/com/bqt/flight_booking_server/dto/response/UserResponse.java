package com.bqt.flight_booking_server.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
@Setter
public class UserResponse {
    private String userId;
    private String email;
    private String phone;
    private String fullname;
    private String role;
}
