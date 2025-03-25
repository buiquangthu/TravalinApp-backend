package com.bqt.flight_booking_server.service;

import com.bqt.flight_booking_server.configuration.JwtTokenProvider;
import com.bqt.flight_booking_server.dto.request.LoginRequest;
import com.bqt.flight_booking_server.dto.request.RegisterRequest;
import com.bqt.flight_booking_server.dto.response.UserResponse;
import com.bqt.flight_booking_server.entity.User;
import com.bqt.flight_booking_server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    // Check xem trong db email này đã tồn tại hay chưa
    public User getUserbyEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Khong tim thay nguoi dung")
        );
    }

    // dang ky tai khoan
    public UserResponse registerUser(RegisterRequest request){
        if(userRepository.existByEmail(request.getEmail())){
            throw new RuntimeException("Email already in use");
        }
        User user = new User(request.getEmail(), passwordEncoder.encode(request.getPassword()));

        user = userRepository.save(user);
        return UserResponse.builder()
                .id(user.getUserId())
                .email(user.getEmail())
                .build();
    }

    // login
    public String loginUser(LoginRequest request){
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            throw new RuntimeException("Invalid email or password");

        return jwtTokenProvider.generrateToken(request.getEmail());
    }
}
