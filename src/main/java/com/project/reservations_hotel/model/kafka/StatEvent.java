package com.project.reservations_hotel.model.kafka;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatEvent {

    private Long userId;

    private LocalDate checkInDate;

    private LocalDate departureDate;

    private Instant createdDate;
}
