package com.project.reservations_hotel.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "rooms")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @Column(name = "room_number")
    private int roomNumber;

    @Column(name = "room_price")
    private int price;

    @Column(name = "max_size")
    private int maxSize;

    @ElementCollection(targetClass = Instant.class, fetch = FetchType.EAGER)
    @JoinTable(name = "room_booked_date", joinColumns = @JoinColumn(name = "room_id"))
    @Column(name = "booked_date")
    @ToString.Exclude
    @Builder.Default
    List<Instant> bookedDates = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hostel_id", nullable = false)
    @ToString.Exclude
    private Hostel hostel;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    @ToString.Exclude
    @Builder.Default
    private List<Booking> bookings = new ArrayList<>();

    @Override
    public final boolean equals(Object o) {
        if(this == o) return true;
        if(o == null) return false;

        Class<?> oEffectiveClass = o instanceof HibernateProxy
                ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass()
                : o.getClass();

        Class<?> thisEffectiveClass = this instanceof HibernateProxy
                ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass()
                : this.getClass();

        if(thisEffectiveClass != oEffectiveClass) return false;

        Room room = (Room) o;

        return getId() != null && Objects.equals(getId(), room.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy
                ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode()
                : getClass().hashCode();
    }
}
