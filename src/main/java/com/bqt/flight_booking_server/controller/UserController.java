package com.bqt.flight_booking_server.controller;

import com.bqt.flight_booking_server.dto.request.LoginRequest;
import com.bqt.flight_booking_server.dto.request.RegisterRequest;
import com.bqt.flight_booking_server.dto.response.JwtResponse;
import com.bqt.flight_booking_server.dto.response.UserResponse;
import com.bqt.flight_booking_server.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody RegisterRequest request){
        UserResponse userResponse = userService.registerUser(request);
        return ResponseEntity.ok(
                UserResponse.builder()
                        .id(userResponse.getId())
                        .email(userResponse.getEmail())
                        .build()
        );
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> loginUser(@Valid @RequestBody LoginRequest request){
        String token = userService.loginUser(request);

        return ResponseEntity.ok(
                JwtResponse.builder()
                        .token(token)
                        .build()
        );
    }

}
