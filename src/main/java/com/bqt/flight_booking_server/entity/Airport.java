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
    @Column(length = 10)
    private String airportCode; // ma san bay

    private String airportName; // ten san bay
    private String location; // dia diem
}
