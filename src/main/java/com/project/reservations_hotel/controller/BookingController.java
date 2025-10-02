package com.project.reservations_hotel.controller;

import com.project.reservations_hotel.entity.Booking;
import com.project.reservations_hotel.mapper.BookingMapper;
import com.project.reservations_hotel.model.kafka.StatEvent;
import com.project.reservations_hotel.model.request.BookingRequest;
import com.project.reservations_hotel.model.response.BookingListResponse;
import com.project.reservations_hotel.model.response.BookingResponse;
import com.project.reservations_hotel.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/v1/booking")
@RequiredArgsConstructor
public class BookingController {

    @Value("${app.kafka.bookingTopic}")
    private String bookingTopic;

    private final BookingService bookingService;

    private final BookingMapper bookingMapper;

    private final KafkaTemplate<String, StatEvent> kafkaTemplate;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BookingListResponse> findAll() {
        return ResponseEntity.ok(
                bookingMapper.bookingListToListResponse(bookingService.findAll())
        );
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<BookingResponse> bookingRoom(@RequestBody @Validated BookingRequest bookingRequest) {
        Booking booking = bookingService.bookingOnFreeDate(bookingMapper.requestToBooking(bookingRequest));

        kafkaTemplate.send(bookingTopic, StatEvent.builder()
                        .userId(booking.getUser().getId())
                        .checkInDate(LocalDate.parse(booking.getCheckInDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                        .departureDate(LocalDate.parse(booking.getDepartureDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                        .createdDate(Instant.now())
                .build());

        return ResponseEntity.ok(
                bookingMapper.bookingToResponse(booking)
        );
    }
}
