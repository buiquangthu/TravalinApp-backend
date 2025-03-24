package com.bqt.flight_booking_server.service;

import com.bqt.flight_booking_server.configuration.JwtTokenProvider;
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

    public UserResponse registerUser(RegisterRequest request){
        if(userRepository.existByEmail(request.getEmail())){
            throw new RuntimeException("Email already in use");
        }
        User user = new User(request.getEmail(), passwordEncoder.encode(request.getPassword()));

        user = userRepository.save(user)
        return new UserResponse(user.getUserId(), user.getEmail());
    }


}
