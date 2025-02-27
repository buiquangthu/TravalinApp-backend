package com.bqt.flight_booking_server.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreationRequest {
    @NotBlank
    private String username;
    private String password;
    private String email;
    private String role;
}
