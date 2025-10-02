package com.project.reservations_hotel.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class BookingListResponse {

    List<BookingResponse> bookingList = new ArrayList<>();

}
