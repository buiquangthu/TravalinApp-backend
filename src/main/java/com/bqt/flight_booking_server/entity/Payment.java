package com.bqt.flight_booking_server.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @OneToOne
    @JoinColumn(name = "bookingId", nullable = false)
    private Booking booking;

    private String paymentMethod;
    private double amount;
    private Date transactionDateTime; // thoi gian thanh toan
    private String transactionStatus; // Succes, Failed, Processing
}
