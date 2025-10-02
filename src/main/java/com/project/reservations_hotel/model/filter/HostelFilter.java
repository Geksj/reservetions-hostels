package com.project.reservations_hotel.model.filter;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HostelFilter {

    private Long id;

    private String title;

    private String adTitle;

    private Integer pageSize;

    private Integer pageNumber;

    private String city;

    private String address;

    private Integer distanceFromCenter;

    private Float rating;

    private Integer ratingCount;

}
