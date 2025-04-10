package com.bqt.flight_booking_server.configuration;

import com.bqt.flight_booking_server.exception.AppException;
import com.bqt.flight_booking_server.exception.ErrorCode;
import com.bqt.flight_booking_server.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
public class UserDetailsServiceConfig {

    UserRepository userRepository;

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return email -> userRepository.findByEmail(email)
                .map(user -> User.withUsername(user.getEmail())
                        .password(user.getPassword())
                        .authorities("ROLE_" + user.getRole()) // Chuyển role thành quyền hạn
                        .build()
                )
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
    }
}
