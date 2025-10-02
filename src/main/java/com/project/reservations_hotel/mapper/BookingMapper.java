package com.project.reservations_hotel.mapper;

import com.project.reservations_hotel.entity.Booking;
import com.project.reservations_hotel.entity.Room;
import com.project.reservations_hotel.entity.User;
import com.project.reservations_hotel.model.request.BookingRequest;
import com.project.reservations_hotel.model.response.BookingListResponse;
import com.project.reservations_hotel.model.response.BookingResponse;
import com.project.reservations_hotel.service.RoomService;
import com.project.reservations_hotel.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookingMapper {

    private final UserService userService;

    private final RoomService roomService;

    public Booking requestToBooking(BookingRequest request) {
        User user = userService.findById(request.getUserId());
        Room room = roomService.findById(request.getRoomId());

        return Booking.builder()
                .checkInDate(request.getCheckInDate())
                .departureDate(request.getDepartureDate())
                .user(user)
                .room(room)
                .build();
    }

    public Booking requestToBooking(Long bookingId,BookingRequest request) {
        Booking booking = requestToBooking(request);

        booking.setId(bookingId);

        return booking;
    }

    public BookingResponse bookingToResponse(Booking booking) {
        return BookingResponse.builder()
                .id(booking.getId())
                .checkInDate(booking.getCheckInDate())
                .departureDate(booking.getDepartureDate())
                .userId(booking.getUser().getId())
                .roomId(booking.getRoom().getId())
                .build();
    }


    public BookingListResponse bookingListToListResponse(List<Booking> bookings) {
        BookingListResponse response = new BookingListResponse();

          response.setBookingList(bookings.stream()
                .map(this::bookingToResponse)
                .toList());

          return response;
    }

}
