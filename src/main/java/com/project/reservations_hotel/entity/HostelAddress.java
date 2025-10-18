package com.project.reservations_hotel.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HostelAddress {

    private String city;

    private String street;

}
