package com.project.reservations_hotel.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomResponse {

    private Long id;

    private String title;

    private String description;

    private int roomNumber;

    private int price;

    private int maxSize;

    List<String> bookedDates;

    private Long hostelId;
}
