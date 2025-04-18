package com.bqt.flight_booking_server.repository;

import com.bqt.flight_booking_server.entity.Booking;
import com.bqt.flight_booking_server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUser(User user);
}