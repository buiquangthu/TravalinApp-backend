package com.bqt.flight_booking_server.repository;

import com.bqt.flight_booking_server.entity.Flight;
import com.bqt.flight_booking_server.entity.FlightStatus;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, String> {
    boolean existsByFlightNumber(String flightNumber);

    List<Flight> findByOriginAirport_AirportCodeAndDestinationAirport_AirportCode(String origin, String dest);

    // Tìm theo hãng hàng không
    List<Flight> findByAirlineCode_AirlineCode(String airlineCode);
}
