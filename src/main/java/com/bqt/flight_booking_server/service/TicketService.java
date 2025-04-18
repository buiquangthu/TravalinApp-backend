package com.bqt.flight_booking_server.service;

import com.bqt.flight_booking_server.dto.response.TicketDetailResponse;
import com.bqt.flight_booking_server.dto.response.TicketSummaryResponse;
import com.bqt.flight_booking_server.entity.Booking;
import com.bqt.flight_booking_server.entity.Flight;
import com.bqt.flight_booking_server.entity.User;
import com.bqt.flight_booking_server.exception.AppException;
import com.bqt.flight_booking_server.exception.ErrorCode;
import com.bqt.flight_booking_server.repository.BookingRepository;
import com.bqt.flight_booking_server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;

    public List<TicketSummaryResponse> getMyTickets(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        List<Booking> bookings = bookingRepository.findByUser(user);

        return bookings.stream().map(booking -> {
            Flight flight = booking.getFlight();
            return TicketSummaryResponse.builder()
                    .id(String.valueOf(booking.getBookingId()))
                    .code(flight.getFlightNumber())
                    .price(flight.getPrice())
                    .status(booking.getBookingStatus())
                    .date(booking.getBookingDate().toInstant().toString())
                    .flight(TicketSummaryResponse.FlightInfo.builder()
                            .from(flight.getOriginAirport().getAirportName())
                            .to(flight.getDestinationAirport().getAirportName())
                            .departure(flight.getDepartureDatetime().toString())
                            .arrival(flight.getArrivalDatetime().toString())
                            .build())
                    .build();
        }).collect(Collectors.toList());
    }

    public TicketDetailResponse getTicketDetail(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));

        return TicketDetailResponse.builder()
                .bookingId(booking.getBookingId())
                .flightNumber(booking.getFlight().getFlightNumber())
                .airline(booking.getFlight().getAirlineCode().getAirlineName())
                .departureTime(booking.getFlight().getDepartureDatetime().toString())
                .arrivalTime(booking.getFlight().getArrivalDatetime().toString())
                .origin(booking.getFlight().getOriginAirport().getAirportName())
                .destination(booking.getFlight().getDestinationAirport().getAirportName())
                .passengerName(booking.getUser().getFullname())
                .passengerPhone(booking.getUser().getPhone())
                .status(booking.getBookingStatus())
                .price(booking.getFlight().getPrice())
                .build();
    }
}
