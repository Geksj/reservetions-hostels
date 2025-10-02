package com.project.reservations_hotel.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "hostels")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hostel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(name = "ad_title")
    private String adTitle;

    @Column(name = "distance_from_center")
    private int distanceFromCenter;

    private HostelAddress address;

    private HostelRating rating;

    @OneToMany(mappedBy = "hostel", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Room> rooms = new ArrayList<>();

}
