package com.bqt.flight_booking_server.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long flightId;

    private String flightNumber;
    private Date departureDatetime;
    private Date arrivalDatetime;
    private String originAirportCode;
    private String destinationAirportCode;
    private int availableSeats;
    private String status;

    @ManyToOne
    @JoinColumn(name = "airlineId", nullable = false)
    private Airline airline;

}
