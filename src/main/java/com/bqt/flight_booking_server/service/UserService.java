package com.bqt.flight_booking_server.service;

import com.bqt.flight_booking_server.configuration.JwtTokenProvider;
import com.bqt.flight_booking_server.dto.request.*;
import com.bqt.flight_booking_server.dto.response.JwtResponse;
import com.bqt.flight_booking_server.dto.response.UserResponse;
import com.bqt.flight_booking_server.entity.Role;
import com.bqt.flight_booking_server.entity.User;
import com.bqt.flight_booking_server.exception.AppException;
import com.bqt.flight_booking_server.exception.ErrorCode;
import com.bqt.flight_booking_server.repository.UserRepository;
import jakarta.annotation.PostConstruct;
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

    @PostConstruct
    public void innitAdminAccount(){
        if(!userRepository.existsByEmail("admin@booking.com")){
            User admin = User.builder()
                    .email("admin@booking.com")
                    .password(passwordEncoder.encode("admin"))
                    .role(Role.ADMIN)
                    .build();
            userRepository.save(admin);
        }
    }

    // Check xem trong db email này đã tồn tại hay chưa
    public User getUserbyEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(
                () -> new AppException(ErrorCode.USER_EXISTED)
        );
    }


    // dang ky tai khoan
    public UserResponse registerUser(RegisterRequest request){
        if(userRepository.existsByEmail(request.getEmail())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        User user = new User(request.getEmail(), passwordEncoder.encode(request.getPassword()), request.getPhone(),request.getFullname());

        user.setRole(Role.USER);

        user = userRepository.save(user);

        return UserResponse.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .fullname(user.getFullname())
                .phone(user.getPhone())
                .role(user.getRole().name())
                .build();
    }

    // dang nhap
    public JwtResponse loginUser(LoginRequest request){
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            throw new AppException(ErrorCode.UNAUTHORIZED);

        String accessToken = jwtTokenProvider.generateToken(user);

        return JwtResponse.builder()
                .accessToken(accessToken)
                .build();
    }

    // Quen mat khau
    public String forgotPassword(ForgotPasswordRequest request){
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());

        if (userOptional.isEmpty())
            throw new AppException(ErrorCode.USER_NOT_FOUND);

        // tao ma otp 5 so
        String otp = String.format("%05d", new Random().nextInt(100000));

        // luu OTP qua email
        redisTemplate.opsForValue().set("OTP_" + request.getEmail(), otp, 2, TimeUnit.MINUTES);

        // gui otp qua email
        emailService.sendOtpEmail(request.getEmail(), otp);

        return "OTP has been sent to your email";
    }


    // xac thuc otp
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
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);

        return "Password reset successfully";
    }



    public UserResponse getMyInfo(String token){

        token =token.replace("Bearer ","");
        String email = jwtTokenProvider.getEmailFromToken(token);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        return UserResponse.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .phone(user.getPhone())
                .fullname(user.getFullname())
                .role(user.getRole().name())
                .build();
    }
}
