package com.bqt.flight_booking_server.service;

import com.bqt.flight_booking_server.entity.User;
import com.bqt.flight_booking_server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // Check xem trong db email này đã tồn tại hay chưa
    public User getUserbyEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Khong tim thay nguoi dung")
        );
    }
}
