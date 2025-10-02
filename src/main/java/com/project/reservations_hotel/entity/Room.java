package com.project.reservations_hotel.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "rooms")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @Column(name = "room_number")
    private int roomNumber;

    private int price;

    @Column(name = "max_size")
    private int maxSize;

    @ElementCollection(targetClass = Instant.class, fetch = FetchType.EAGER)
    @JoinTable(name = "room_booked_date", joinColumns = @JoinColumn(name = "room_id"))
    @Column(name = "booked_date")
    @Builder.Default
    List<Instant> bookedDates = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hostel_id", nullable = false)
    private Hostel hostel;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Booking> bookings = new ArrayList<>();

}
