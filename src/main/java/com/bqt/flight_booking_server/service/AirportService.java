package com.bqt.flight_booking_server.service;

import com.bqt.flight_booking_server.dto.response.AirportResponse;
import com.bqt.flight_booking_server.entity.Airport;
import com.bqt.flight_booking_server.exception.AppException;
import com.bqt.flight_booking_server.exception.ErrorCode;
import com.bqt.flight_booking_server.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AirportService {

    private final AirportRepository airportRepository;

    public List<AirportResponse> getAllAirports() {
        try {
            return airportRepository.findAll()
                    .stream()
                    .map(this::mapToResponse)
                    .collect(Collectors.toList());
        }catch (Exception e){
            throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public AirportResponse getAirportByCode(String code) {
        Airport airport = airportRepository.findById(code)
                .orElseThrow(() -> new RuntimeException("Airport not found with code: " + code));
        return mapToResponse(airport);
    }

    private AirportResponse mapToResponse(Airport airport) {
        return AirportResponse.builder()
                .airport_code(airport.getAirportCode())
                .airport_name(airport.getAirportName())
                .location(airport.getLocation())
                .build();
    }
}
