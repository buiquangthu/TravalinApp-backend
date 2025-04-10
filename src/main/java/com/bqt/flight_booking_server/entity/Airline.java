package com.bqt.flight_booking_server.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "airlines")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Airline {
    @Id
    @Column(name = "airline_code", length = 10)
    private String airlineCode;

    @Column(name = "airline_name", nullable = false, unique = true)
    private String airlineName;

    @Column(name = "contact_number", length = 15)
    private String contactNumber;  // Bổ sung số liên hệ

    @Column(name = "logo_url")
    private String logoUrl;
}