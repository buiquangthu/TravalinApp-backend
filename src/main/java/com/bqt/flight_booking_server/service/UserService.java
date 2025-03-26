package com.bqt.flight_booking_server.service;

import com.bqt.flight_booking_server.configuration.JwtTokenProvider;
import com.bqt.flight_booking_server.dto.request.*;
import com.bqt.flight_booking_server.dto.response.JwtResponse;
import com.bqt.flight_booking_server.dto.response.UserResponse;
import com.bqt.flight_booking_server.entity.User;
import com.bqt.flight_booking_server.exception.AppException;
import com.bqt.flight_booking_server.exception.ErrorCode;
import com.bqt.flight_booking_server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    @Autowired
    private EmailService emailService;


    // Check xem trong db email này đã tồn tại hay chưa
    public User getUserbyEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Khong tim thay nguoi dung")
        );
    }


    // dang ky tai khoan
    public UserResponse registerUser(RegisterRequest request){
        if(userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already in use");
        }
        User user = new User(request.getEmail(), passwordEncoder.encode(request.getPassword()), request.getPhone(),request.getFullname());

        user = userRepository.save(user);
        return UserResponse.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .build();
    }

    // dang nhap
    public JwtResponse loginUser(LoginRequest request){
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new AppException(ErrorCode.USER_EXISTED));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            throw new AppException(ErrorCode.USER_NOT_EXISTED);

        String accessToken = jwtTokenProvider.generateToken(user.getEmail());

        return JwtResponse.builder()
                .accessToken(accessToken)
                .build();
    }

    // Quen mat khau
    public String forgotPassword(ForgotPasswordRequest request){
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());

        if (userOptional.isEmpty())
            throw new AppException(ErrorCode.USER_NOT_EXISTED);

        // tao ma otp 5 so
        String otp = String.format("%05d", new Random().nextInt(100000));

        // luu OTP qua email
        redisTemplate.opsForValue().set("OTP_" + request.getEmail(), otp, 2, TimeUnit.MINUTES);

        // gui otp qua email
        emailService.sendOtpEmail(request.getEmail(), otp);

        return "OTP has been sent to your email";
    }


    // xac thu otp
    public String verifyOtp(VerifyOtpRequest request){
        String storeOtp = redisTemplate.opsForValue().get("OTP_" + request.getEmail());
        if(storeOtp == null || !storeOtp.equals(request.getOtp())){
            throw new AppException(ErrorCode.INVALID_OTP);
        }
        // neu otp hop le xoa khoi redis
        redisTemplate.delete("OTP_" + request.getEmail());
        return "OTP verified successfully. You can now reset your password";
    }

    public String resetPassword(ResetPasswordRequest request){
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        user.setPassword(passwordEncoder.encode(request.getNewPasswordl()));
        userRepository.save(user);

        return "Password reset successfully";
    }
}
