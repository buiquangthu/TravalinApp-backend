package com.bqt.flight_booking_server.controller;

import com.bqt.flight_booking_server.dto.response.AirportResponse;
import com.bqt.flight_booking_server.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/airports")
public class AirportController {
    private final AirportService airportService;

    @GetMapping
    public ResponseEntity<List<AirportResponse>> getAllAirports(){
        return ResponseEntity.ok(airportService.getAllAirports());
    }

}
