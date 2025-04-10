package com.bqt.flight_booking_server.repository;

import com.bqt.flight_booking_server.entity.Airport;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AirportRepository extends JpaRepository<Airport, String> {
    boolean existsByAirportName(String airportName);
}