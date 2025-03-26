package com.bqt.flight_booking_server.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class VerifyOtpRequest {

    @NotBlank(message = "Email can not blank")
    private String email;

    @NotBlank(message = "OTP can not blank")
    @Size(min = 5, max = 5, message = "OTP must be exactly 5 digits")
    private String otp;
}
