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

    @NotBlank(message = "PhoneNumber cannot be blank") // khong cho phep bo trong
    @Pattern(regexp = "^[0-9]{10,15}$", message = "Phone number must be between 10-15 digits")
    private String phone;

    @NotBlank(message = "Fullname cannot be blank") // khong cho phep bo trong
    private String fullname;



}
