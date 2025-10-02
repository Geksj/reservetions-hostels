package com.project.reservations_hotel.controller;

import com.project.reservations_hotel.entity.Hostel;
import com.project.reservations_hotel.mapper.HostelMapper;
import com.project.reservations_hotel.model.filter.HostelFilter;
import com.project.reservations_hotel.model.request.HostelRequest;
import com.project.reservations_hotel.model.request.RatingRequest;
import com.project.reservations_hotel.model.response.HostelFilterResponse;
import com.project.reservations_hotel.model.response.HostelListResponse;
import com.project.reservations_hotel.model.response.HostelResponse;
import com.project.reservations_hotel.service.HostelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/hostels")
@RequiredArgsConstructor
public class HostelController {

    private final HostelService hostelService;

    private final HostelMapper hostelMapper;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<HostelListResponse> findAll() {
        return ResponseEntity.ok(
                hostelMapper.hostelListToResponseList(hostelService.findAll())
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<HostelResponse> findById(@PathVariable("id") Long hostelId) {
        return ResponseEntity.ok(
                hostelMapper.hostelToResponse(hostelService.findById(hostelId))
        );
    }

    @GetMapping("/filter")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<HostelFilterResponse> findBy(HostelFilter filter) {
        return ResponseEntity.ok(
                hostelMapper.hostelListToHostelFilterResponse(
                        hostelService.findBy(filter)
                ));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HostelResponse> createHostel(@RequestBody @Validated HostelRequest request) {
        Hostel hostel = hostelService.createHostel(hostelMapper.requestToHostel(request));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(hostelMapper.hostelToResponse(hostel));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HostelResponse> updateHostel(@PathVariable("id") Long hostelId, @RequestBody @Validated HostelRequest request) {
        Hostel updatedHostel = hostelService.updateHostel(hostelMapper.requestToHostel(hostelId, request));

        return ResponseEntity.ok(hostelMapper.hostelToResponse(updatedHostel));
    }

    @PutMapping("/rating")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<HostelResponse> changeRating(@RequestBody @Validated RatingRequest request) {
        return ResponseEntity.ok(hostelMapper.hostelToResponse(hostelService.addRating(request)));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long hostelId) {
        hostelService.deleteById(hostelId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
