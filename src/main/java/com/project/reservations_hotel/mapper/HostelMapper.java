package com.project.reservations_hotel.mapper;

import com.project.reservations_hotel.entity.Hostel;
import com.project.reservations_hotel.entity.HostelAddress;
import com.project.reservations_hotel.model.request.HostelRequest;
import com.project.reservations_hotel.model.response.HostelFilterResponse;
import com.project.reservations_hotel.model.response.HostelListResponse;
import com.project.reservations_hotel.model.response.HostelResponse;
import com.project.reservations_hotel.model.response.RoomResponse;
import com.project.reservations_hotel.service.HostelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class HostelMapper {

    private final RoomMapper roomMapper;

    private final HostelService hostelService;

    public HostelFilterResponse hostelListToHostelFilterResponse(List<Hostel> hostels) {
        HostelFilterResponse response = new HostelFilterResponse();

        response.setHostelCount(hostelService.count());
        response.setHostels(hostelListToResponseList(hostels));

        return response;
    }

    public Hostel requestToHostel(HostelRequest hostelRequest) {
        Hostel hostel = new Hostel();
        HostelAddress hostelAddress = new HostelAddress();

        hostelAddress.setCity(hostelRequest.getCity());
        hostelAddress.setStreet(hostelRequest.getAddress());

        return Hostel.builder()
                .title(hostelRequest.getTitle())
                .adTitle(hostelRequest.getAdTitle())
                .distanceFromCenter(hostelRequest.getDistanceFromCenter())
                .address(hostelAddress)
                .build();
    }

    public Hostel requestToHostel(Long hostelId, HostelRequest hostelRequest) {
        Hostel hostel = requestToHostel(hostelRequest);

        hostel.setId(hostelId);

        return hostel;
    }

    public HostelResponse hostelToResponse(Hostel hostel) {
        List<RoomResponse> roomResponses = hostel.getRooms().stream()
                .map(roomMapper::roomToResponse)
                .toList();

        return HostelResponse.builder()
                .id(hostel.getId())
                .title(hostel.getTitle())
                .adTitle(hostel.getAdTitle())
                .address(hostel.getAddress().getStreet())
                .city(hostel.getAddress().getCity())
                .distanceFromCenter(hostel.getDistanceFromCenter())
                .rating(hostel.getRating().getRating())
                .ratingCount(hostel.getRating().getRatingCount())
                .rooms(roomResponses)
                .build();
    }

    public HostelListResponse hostelListToResponseList(List<Hostel> hostels) {
        HostelListResponse response = new HostelListResponse();

        response.setHostels(hostels.stream()
                .map(this::hostelToResponse)
                .toList());

        return response;
    }

}
