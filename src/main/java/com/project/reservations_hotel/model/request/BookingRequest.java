package com.project.reservations_hotel.model.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequest {

    @NotBlank
    private String checkInDate;

    @NotBlank
    private String departureDate;

    @NotBlank
    @Min(1)
    private Long roomId;

    @NotBlank
    @Min(1)
    private Long userId;

}
