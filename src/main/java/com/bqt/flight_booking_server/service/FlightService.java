package com.bqt.flight_booking_server.service;

import com.bqt.flight_booking_server.dto.request.FlightRequest;
import com.bqt.flight_booking_server.dto.request.FlightSearchRequest;
import com.bqt.flight_booking_server.dto.response.FlightResponse;
import com.bqt.flight_booking_server.entity.Airline;
import com.bqt.flight_booking_server.entity.Airport;
import com.bqt.flight_booking_server.entity.Flight;
import com.bqt.flight_booking_server.exception.AppException;
import com.bqt.flight_booking_server.exception.ErrorCode;
import com.bqt.flight_booking_server.repository.AirlineRepository;
import com.bqt.flight_booking_server.repository.AirportRepository;
import com.bqt.flight_booking_server.repository.FlightRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FlightService {
    @Autowired
    FlightRepository flightRepository;
    @Autowired
    AirlineRepository airlineRepository;
    @Autowired
    AirportRepository airportRepository;

    public FlightResponse createFlight(FlightRequest request){
        Airline airline = airlineRepository.findById(request.getAirlineCode())
                .orElseThrow(() -> new AppException(ErrorCode.AIRLINE_NOT_FOUND, ""));

        Airport origin = airportRepository.findById(request.getOriginAirportCode())
                .orElseThrow(() -> new AppException(ErrorCode.AIRPORT_NOT_FOUND));

        Airport destination = airportRepository.findById(request.getDestinationAirportCode())
                .orElseThrow(() -> new AppException(ErrorCode.AIRPORT_NOT_FOUND));

        Flight flight = Flight.builder()
                .flightNumber(request.getFlightNumber())
                .airlineCode(airline)
                .originAirport(origin)
                .destinationAirport(destination)
                .departureDatetime(request.getDepartureDatetime())
                .arrivalDatetime(request.getArrivalDatetime())
                .availableSeats(request.getAvailableSeats())
                .status(request.getStatus())
                .duration(request.getDuration())
                .price(request.getPrice())
                .stops(request.getStops())
                .flightClass(request.getFlightClass())
                .build();
        Flight saved = flightRepository.save(flight);

        return mapToResponse(saved, airline, origin, destination);
    }

    // lay thong tin toan bo chuyen bay
    public List<FlightResponse> getAllFlights(){
        try {
            List<Flight> flights = flightRepository.findAll();
            return flights.stream().map(
                    flight -> mapToResponse(
                            flight,
                            flight.getAirlineCode(),
                            flight.getOriginAirport(),
                            flight.getDestinationAirport()
                    )
            ).collect(Collectors.toList());
        } catch (Exception e) {
            throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public List<FlightResponse> searchFlights(FlightSearchRequest request) {
        try {
            List<Flight> flights = flightRepository.searchFlightsFlexible(
                    request.getOriginAirportCode(),
                    request.getDestinationAirportCode(),
                    request.getDepartureDate()
            );

            return flights.stream()
                    .map(flight -> mapToResponse(
                            flight,
                            flight.getAirlineCode(),
                            flight.getOriginAirport(),
                            flight.getDestinationAirport()
                    ))
                    .collect(Collectors.toList());
        }catch (Exception e){
            throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }









    private FlightResponse mapToResponse(Flight flight, Airline airline, Airport origin, Airport destination) {
        return FlightResponse.builder()
                .flightId(flight.getFlightId())
                .flightNumber(flight.getFlightNumber())
                .airlineCode(airline.getAirlineCode())
                .airlineName(airline.getAirlineName())
                .originAirportCode(origin.getAirportCode())
                .originAirportName(origin.getAirportName())
                .destinationAirportCode(destination.getAirportCode())
                .destinationAirportName(destination.getAirportName())
                .departureDatetime(flight.getDepartureDatetime())
                .arrivalDatetime(flight.getArrivalDatetime())
                .availableSeats(flight.getAvailableSeats())
                .status(flight.getStatus())
                .duration(flight.getDuration())
                .price(flight.getPrice())
                .stops(flight.getStops())
                .flightClass(flight.getFlightClass())
                .build();
    }

}
