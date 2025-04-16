package com.bqt.flight_booking_server.repository;

import com.bqt.flight_booking_server.entity.Flight;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    boolean existsByFlightNumber(String flightNumber);

    List<Flight> findByOriginAirport_AirportCodeAndDestinationAirport_AirportCode(String origin, String dest);

    // Tìm theo hãng hàng không
    List<Flight> findByAirlineCode_AirlineCode(String airlineCode);


    // tìm chuyến bay mở bán theo điểm đi, đến, ngày khởi hành
    @Query(value = """
        SELECT * FROM flights
        WHERE origin_airport_code = :originCode
          AND destination_airport_code = :destinationCode
          AND DATE(departure_datetime) = :departureDate
          AND status = 'Đang mở bán'
        """, nativeQuery = true)
    List<Flight> searchFlights(
            @Param("originCode") String originAirportCode,
            @Param("destinationCode") String destinationAirportCode,
            @Param("departureDate") LocalDate departureDate
    );

    @Query(value = """
    SELECT * FROM flights
    WHERE status = 'Đang mở bán'
      AND (:originAirportCode IS NULL OR origin_airport_code = :originAirportCode)
      AND (:destinationAirportCode IS NULL OR destination_airport_code = :destinationAirportCode)
      AND (:departureDate IS NULL OR DATE(departure_datetime) = :departureDate)
    """, nativeQuery = true)
    List<Flight> searchFlightsFlexible(
            String originAirportCode,
            String destinationAirportCode,
            LocalDate departureDate
    );

}
