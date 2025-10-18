package com.project.reservations_hotel.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.beans.FeatureDescriptor;
import java.util.Objects;

@Entity(name = "bookings")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "check_in_date")
    private String checkInDate;

    @Column(name = "departure_date")
    private String departureDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    @ToString.Exclude
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @ToString.Exclude
    private User user;

    @Override
    public final boolean equals(Object o) {
        if(this == o) return true;
        if(o == null) return false;

        Class<?> oEffectiveClass = o instanceof HibernateProxy
                ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass()
                : this.getClass();

        Class<?> thisEffectiveClass = this instanceof HibernateProxy
                ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass()
                : this.getClass();

        if(thisEffectiveClass != oEffectiveClass) return false;

        Booking booking = (Booking) o;

        return getId() != null && Objects.equals(getId(), booking.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, checkInDate, departureDate, room, user);
    }
}
