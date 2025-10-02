package com.project.reservations_hotel.service;

import com.project.reservations_hotel.entity.Booking;
import com.project.reservations_hotel.entity.Room;
import com.project.reservations_hotel.exception.RoomAlreadyBookedException;
import com.project.reservations_hotel.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;

    private final RoomService roomService;

    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    public Booking bookingOnFreeDate(Booking booking) {
        Room room = roomService.findById(booking.getRoom().getId());
        LocalDate checkInDT = LocalDate.parse(booking.getCheckInDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate departureDT = LocalDate.parse(booking.getDepartureDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Instant checkInInstant = checkInDT.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant();

        Instant departureInstant = departureDT.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant();

        if(checkInDT.isBefore(LocalDate.now())) {
            throw new RoomAlreadyBookedException("Date cannot be in the past");
        }

        if(!room.getBookedDates().contains(checkInInstant)
                && !room.getBookedDates().contains(departureInstant)) {
            Instant between = checkInInstant;
            boolean flag = false;

            for(int i = 0; i < 2; i++) {

                if(flag) {
                    between = checkInInstant;
                    room.getBookedDates().add(checkInInstant);
                    room.getBookedDates().add(departureInstant);
                }

                while(between.isBefore(departureInstant.minus(Duration.ofDays(1)))) {

                    between = between.plus(Duration.ofDays(1));

                    if(!flag && room.getBookedDates().contains(between)) {
                       throw new RoomAlreadyBookedException("Room is already booked for date:");
                    }

                    if(flag) {
                        room.getBookedDates().add(between);
                    }

                }
                flag = true;
            }
        } else {
            throw new RoomAlreadyBookedException("Rooms is booked");
        }

        roomService.update(room);

        return bookingRepository.save(booking);
    }
}
