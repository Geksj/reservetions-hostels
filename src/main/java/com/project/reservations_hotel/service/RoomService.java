package com.project.reservations_hotel.service;

import com.project.reservations_hotel.entity.Hostel;
import com.project.reservations_hotel.entity.Room;
import com.project.reservations_hotel.model.filter.RoomFilter;
import com.project.reservations_hotel.repository.RoomRepository;
import com.project.reservations_hotel.repository.specification.RoomSpecification;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    private final HostelService hostelService;

    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    public List<Room> findBy(RoomFilter filter) {
        return roomRepository.findAll(RoomSpecification.withFilter(filter),
                PageRequest.of(
                        filter.getPageNumber(),
                        filter.getPageSize()
                )).getContent();
    }

    public Room findById(Long roomId) {
        return roomRepository.findById(roomId)
                .orElseThrow(() -> new EntityNotFoundException("Room not found by id: " + roomId));
    }

    public Room create(Room room) {
        return roomRepository.save(room);
    }

    public Room update(Room room) {
        Room existsRoom = findById(room.getId());
        Hostel hostel = hostelService.findById(room.getHostel().getId());

        existsRoom.setId(room.getId());
        existsRoom.setTitle(room.getTitle());
        existsRoom.setHostel(hostel);
        existsRoom.setRoomNumber(room.getRoomNumber());
        existsRoom.setBookedDates(room.getBookedDates());
        existsRoom.setMaxSize(room.getMaxSize());
        existsRoom.setPrice(room.getPrice());

        return roomRepository.save(existsRoom);
    }

    public void deleteById(Long roomId) {
        roomRepository.deleteById(roomId);
    }

    public Long count() {
        return roomRepository.count();
    }

}
