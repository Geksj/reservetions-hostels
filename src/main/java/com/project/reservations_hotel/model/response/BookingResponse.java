package com.project.reservations_hotel.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingResponse {

    private Long id;

    private String checkInDate;

    private String departureDate;

    private Long roomId;

    private Long userId;


}
