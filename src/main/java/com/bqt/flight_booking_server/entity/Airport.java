package com.bqt.flight_booking_server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "airports")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Airport {
    @Id
    @Column(name = "airport_code", length = 10)
    private String airportCode; // ma san bay

    @Column(name = "airport_name", nullable = false)
    private String airportName; // ten san bay

    @Column(name = "location", nullable = false)
    private String location; // dia diem
    
}
