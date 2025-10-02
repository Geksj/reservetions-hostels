package com.project.reservations_hotel.mapper;

import com.project.reservations_hotel.entity.Hostel;
import com.project.reservations_hotel.entity.Room;
import com.project.reservations_hotel.model.request.RoomRequest;
import com.project.reservations_hotel.model.response.RoomFilterResponse;
import com.project.reservations_hotel.model.response.RoomListResponse;
import com.project.reservations_hotel.model.response.RoomResponse;
import com.project.reservations_hotel.service.BookingService;
import com.project.reservations_hotel.service.HostelService;
import com.project.reservations_hotel.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class RoomMapper {

    private final HostelService hostelService;

    private final RoomService roomService;

    public RoomFilterResponse roomListToRoomFilterResponse(List<Room> rooms) {
        RoomFilterResponse response = new RoomFilterResponse();

        response.setRoomCount(roomService.count());
        response.setRooms(roomListToListResponse(rooms));

        return response;
    }

    public Room requestToRoom(RoomRequest request) {
        Hostel hostelOwner = hostelService.findById(request.getHostelId());

        return Room.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .roomNumber(request.getRoomNumber())
                .hostel(hostelOwner)
                .maxSize(request.getMaxSize())
                .price(request.getPrice())
                .build();
    }

    public Room requestToRoom(Long id, RoomRequest request) {
        Room room = requestToRoom(request);

        room.setId(id);

        return room;
    }

    public RoomResponse roomToResponse(Room room) {


        List<String> dates = room.getBookedDates().stream()
                .map(instant -> instant.atZone(ZoneId.systemDefault()).toLocalDate())
                .map(localDate -> localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .collect(Collectors.toList());

        return RoomResponse.builder()
                .id(room.getId())
                .title(room.getTitle())
                .description(room.getDescription())
                .roomNumber(room.getRoomNumber())
                .bookedDates(dates)
                .maxSize(room.getMaxSize())
                .price(room.getPrice())
                .hostelId(room.getHostel().getId())
                .build();
    }

    public RoomListResponse roomListToListResponse(List<Room> rooms) {
        RoomListResponse response = new RoomListResponse();

        response.setRooms(rooms.stream()
                .map(this::roomToResponse)
                .toList());

        return response;
    }

}
