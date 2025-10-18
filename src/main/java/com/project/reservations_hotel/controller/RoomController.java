package com.project.reservations_hotel.controller;

import com.project.reservations_hotel.entity.Room;
import com.project.reservations_hotel.mapper.RoomMapper;
import com.project.reservations_hotel.model.filter.RoomFilter;
import com.project.reservations_hotel.model.request.RoomRequest;
import com.project.reservations_hotel.model.response.RoomFilterResponse;
import com.project.reservations_hotel.model.response.RoomListResponse;
import com.project.reservations_hotel.model.response.RoomResponse;
import com.project.reservations_hotel.service.RoomService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rooms")
@RequiredArgsConstructor
@Validated
public class RoomController {

    private final RoomService roomService;

    private final RoomMapper roomMapper;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<RoomListResponse> findAll() {
        return ResponseEntity.ok(
                roomMapper.roomListToListResponse(roomService.findAll())
        );
    }

    @GetMapping("/filter")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<RoomFilterResponse> findBy(RoomFilter filter) {
        return ResponseEntity.ok(
                roomMapper.roomListToRoomFilterResponse(roomService.findBy(filter))
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<RoomResponse> findById(@PathVariable("id") @Min(1) Long roomId) {
        return ResponseEntity.ok(
                roomMapper.roomToResponse(roomService.findById(roomId))
        );
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RoomResponse> createRoom(@Valid @RequestBody RoomRequest request) {
        Room createdRoom = roomService.create(roomMapper.requestToRoom(request));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(roomMapper.roomToResponse(createdRoom));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RoomResponse> updateRoom(
            @PathVariable("id") @Min(1) Long roomId,
            @Valid @RequestBody RoomRequest request) {
        Room updatedRoom = roomService.update(roomMapper.requestToRoom(roomId, request));

        return ResponseEntity.ok(roomMapper.roomToResponse(updatedRoom));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable("id") @Min(1) Long roomId) {
        roomService.deleteById(roomId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
