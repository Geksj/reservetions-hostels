package com.project.reservations_hotel.model.filter;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoomFilter {

    private Long id;

    private Integer pageSize;

    private Integer pageNumber;

    private String title;

    private Integer minPrice;

    private Integer maxPrice;

    private Integer maxPeople;

    private String checkInDate;

    private String departureDate;
}
