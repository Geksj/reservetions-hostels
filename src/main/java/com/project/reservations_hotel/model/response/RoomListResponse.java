package com.project.reservations_hotel.model.response;

import lombok.Data;

import java.util.List;

@Data
public class RoomListResponse {

    private List<RoomResponse> rooms;

}
