package com.bqt.flight_booking_server.controller;

import com.bqt.flight_booking_server.dto.response.TicketDetailResponse;
import com.bqt.flight_booking_server.dto.response.TicketSummaryResponse;
import com.bqt.flight_booking_server.service.TicketService;
import com.bqt.flight_booking_server.configuration.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/me")
    public ResponseEntity<List<TicketSummaryResponse>> getMyTickets(@RequestHeader("Authorization") String token) {
        String email = jwtTokenProvider.getEmailFromToken(token.replace("Bearer ", ""));
        return ResponseEntity.ok(ticketService.getMyTickets(email));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketDetailResponse> getTicketDetail(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.getTicketDetail(id));
    }
}