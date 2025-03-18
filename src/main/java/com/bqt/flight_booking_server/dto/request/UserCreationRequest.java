package com.bqt.flight_booking_server.dto.request;


import jakarta.validation.constraints.*;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreationRequest {
    @NotEmpty(message = "MISSING USERNAME")
    @Size(min = 6, message = "INVALID USERNAME")
    private String username;

    @NotEmpty(message = "MISSING PASSWORD")
    @Size(min = 8, message = "INVALID PASSWORD")
    private String password;

    @Email(message = "INVALID EMAIL")
    private String email;
    private String role;
}
