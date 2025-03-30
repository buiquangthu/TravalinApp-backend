package com.bqt.flight_booking_server.controller;

import com.bqt.flight_booking_server.dto.request.*;
import com.bqt.flight_booking_server.dto.response.JwtResponse;
import com.bqt.flight_booking_server.dto.response.UserResponse;
import com.bqt.flight_booking_server.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String ping() {
        log.info("pong");
        return "pong";
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody RegisterRequest request){
        UserResponse userResponse = userService.registerUser(request);
//        return ResponseEntity.ok(UserResponse.builder()
//                        .id(userResponse.getId())
//                        .email(userResponse.getEmail())
//                        .build()
//        );
        return ResponseEntity.ok(userResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> loginUser(@Valid @RequestBody LoginRequest request){
        JwtResponse jwtResponse = userService.loginUser(request);

        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@Valid @RequestBody ForgotPasswordRequest request){
        String response = userService.forgotPassword(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@Valid @RequestBody VerifyOtpRequest request){
        String response = userService.verifyOtp(request);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@Valid @RequestBody ResetPasswordRequest request){
        String  response = userService.resetPassword(request);

        return ResponseEntity.ok(response);
    }

}
