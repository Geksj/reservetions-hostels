package com.project.reservations_hotel.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class HostelListResponse {

    private List<HostelResponse> hostels = new ArrayList<>();

}
