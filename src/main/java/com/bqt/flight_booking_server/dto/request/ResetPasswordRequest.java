package com.bqt.flight_booking_server.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResetPasswordRequest {
    @NotBlank(message = "Email can not blank")
    private String email;

    @NotBlank(message = "New password can not blank")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String newPassword;
}
