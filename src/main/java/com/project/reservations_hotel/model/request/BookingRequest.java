package com.project.reservations_hotel.model.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequest {

    @NotNull
    private String checkInDate;

    @NotNull
    private String departureDate;

    @NotNull
    @Positive
    @Min(1)
    private Long roomId;

    @NotNull
    @Positive
    @Min(1)
    private Long userId;

}
