package com.project.reservations_hotel.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HostelResponse {

    private Long id;

    private String title;

    private String adTitle;

    private String city;

    private String address;

    private int distanceFromCenter;

    private float rating;

    private int ratingCount;

    private List<RoomResponse> rooms;

}
