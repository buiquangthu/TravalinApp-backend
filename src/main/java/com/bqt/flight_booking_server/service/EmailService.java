package com.bqt.flight_booking_server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendOtpEmail(String to, String otp){
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(to);
        message.setSubject("OTP Rest Password");
        message.setText("Your OTP for password reset is: " + otp + ". It is valid for 2 minutes");

        mailSender.send(message);
    }
}
