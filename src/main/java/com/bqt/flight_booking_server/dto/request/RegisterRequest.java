package com.bqt.flight_booking_server.dto.request;


import jakarta.validation.constraints.*;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {

    @Email(message = "Invalid email format") // ktra dinh dang email
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @NotBlank(message = "Password cannot be blank") // khong cho phep bo trong
    @Size(min = 8, message = "Password must be at least 6 characters")
    private String password;

}
