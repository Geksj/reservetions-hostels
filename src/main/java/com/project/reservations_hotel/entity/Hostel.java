package com.project.reservations_hotel.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "hostels")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
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
    @ToString.Exclude
    @Builder.Default
    private List<Room> rooms = new ArrayList<>();

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

        Hostel hostel = (Hostel) o;

        return getId() != null && Objects.equals(getId(), hostel.getId());

    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy
                ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode()
                : getClass().hashCode();
    }
}
