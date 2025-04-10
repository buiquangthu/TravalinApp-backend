package com.bqt.flight_booking_server.controller;

import com.bqt.flight_booking_server.dto.request.FlightRequest;
import com.bqt.flight_booking_server.dto.response.FlightResponse;
import com.bqt.flight_booking_server.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    // Tạo chuyến bay
    @PostMapping("/createFlight")
    public ResponseEntity<FlightResponse> createFlight(@RequestBody FlightRequest request) {
        FlightResponse response = flightService.createFlight(request);
        return ResponseEntity.ok(response);
    }

    // Lấy danh sách tất cả chuyến bay
    @GetMapping
    public ResponseEntity<List<FlightResponse>> getAllFlights() {
        return ResponseEntity.ok(flightService.getAllFlights());
    }

    // Lấy chi tiết chuyến bay theo ID
//    @GetMapping("/{id}")
//    public ResponseEntity<FlightResponse> getFlightById(@PathVariable Long id) {
//        return ResponseEntity.ok(flightService.getFlightById(id));
//    }

    // Xóa chuyến bay
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteFlight(@PathVariable Long id) {
//        flightService.deleteFlight(id);
//        return ResponseEntity.noContent().build();
//    }
}
