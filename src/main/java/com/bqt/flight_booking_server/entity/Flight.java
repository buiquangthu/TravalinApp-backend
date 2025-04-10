package com.bqt.flight_booking_server.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "flights")
@Builder
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightId;

    @Column(name = "flight_number", nullable = false, unique = true)
    private String flightNumber;

    @ManyToOne
    @JoinColumn(name = "airline_code", nullable = false)
    private Airline airlineCode;

    @ManyToOne
    @JoinColumn(name = "origin_airport_code", referencedColumnName = "airport_code")
    private Airport originAirport;

    @ManyToOne
    @JoinColumn(name = "destination_airport_code", referencedColumnName = "airport_code")
    private Airport destinationAirport;


    @Column(name = "departure_datetime", nullable = false)
    private LocalDateTime departureDatetime; // ngay khoi hanh

    @Column(name = "arrival_datetime", nullable = false)
    private LocalDateTime arrivalDatetime; // ngay den du kien

    @Column(name = "available_seats", nullable = false)
    private Integer availableSeats; // so ghế ngồi có sẵn

    @Convert(converter = FlightStatusConverter.class)
    @Column(name = "status", nullable = false)
    private FlightStatus status;

    @Column(name = "duration", nullable = false)
    private Integer duration; // in minutes

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "stops", nullable = false)
    private Integer stops; // diem dừng

    @Enumerated(EnumType.STRING)
    @Column(name = "flight_class", nullable = false)
    private FlightClass flightClass; // hạng ghế


}
