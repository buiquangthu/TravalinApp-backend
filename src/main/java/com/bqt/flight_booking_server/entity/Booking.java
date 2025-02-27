package com.bqt.flight_booking_server.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long BookingId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "flightId", nullable = false)
    private Flight flight;

    private Date bookingDate;
    private String seatNumber;
    private String paymentStatus; // Pending, Paid, Cancelled
    private String bookingStatus; // Confirmed, Waiting, Cancelled
}
