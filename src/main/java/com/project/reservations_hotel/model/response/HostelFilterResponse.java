package com.project.reservations_hotel.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HostelFilterResponse {

    private Long hostelCount;

    private HostelListResponse hostels;

}
