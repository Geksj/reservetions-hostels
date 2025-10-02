package com.project.reservations_hotel.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomFilterResponse {

    private Long roomCount;

    private RoomListResponse rooms;

}
