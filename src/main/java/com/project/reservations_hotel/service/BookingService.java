package com.project.reservations_hotel.service;

import com.project.reservations_hotel.entity.Booking;
import com.project.reservations_hotel.entity.Room;
import com.project.reservations_hotel.exception.RoomAlreadyBookedException;
import com.project.reservations_hotel.repository.BookingRepository;
import com.project.reservations_hotel.utils.AppDateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Service
@Slf4j
public class BookingService {

    private final BookingRepository bookingRepository;

    private final RoomService roomService;

    public BookingService(BookingRepository bookingRepository, RoomService roomService) {
        this.bookingRepository = bookingRepository;
        this.roomService = roomService;
    }

    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    public Booking bookingOnFreeDate(Booking booking) {
        Room room = roomService.findById(booking.getRoom().getId());;

        Instant checkIn = AppDateUtils.parseStringToInstant(booking.getCheckInDate());
        Instant depart = AppDateUtils.parseStringToInstant(booking.getDepartureDate());

        if(checkIn.equals(depart)) {
            throw new RoomAlreadyBookedException("The difficult must be more 1 day between checkIn and depart");
        }

        if(checkIn.isBefore(Instant.now()) && depart.isBefore(Instant.now())) {
            throw new RoomAlreadyBookedException("The date cannot be in the past");
        }

        if(checkIn.isAfter(depart) || depart.isBefore(checkIn)) {
            throw new RoomAlreadyBookedException("The date is incorrect");
        }

        checkOnBookingDates(room, checkIn, depart);
        addDates(room, checkIn, depart.plus(Duration.ofDays(1)));

        roomService.update(room);

        return bookingRepository.save(booking);
    }

    private void addDates(Room room, Instant start, Instant end) {
        while (start.isBefore(end)) {
            room.getBookedDates().add(start);

            start = start.plus(Duration.ofDays(1));
        }
    }

    private void checkOnBookingDates(Room room, Instant start, Instant end) {
        while(start.isBefore(end)) {
            if(isDatesExist(room, start, end)) {
                throw new RoomAlreadyBookedException("The Room is booked for those dates!");
            }

            start = start.plus(Duration.ofDays(1));
            end = end.minus(Duration.ofDays(1));
        }
    }

    private boolean isDatesExist(Room room, Instant checkIn, Instant depart) {
        return room.getBookedDates().contains(checkIn) ||
                room.getBookedDates().contains(depart);
    }
}
