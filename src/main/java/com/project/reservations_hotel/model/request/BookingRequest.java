package com.project.reservations_hotel.model.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    @Min(1)
    private Long roomId;

    @NotNull
    @Min(1)
    private Long userId;

}
