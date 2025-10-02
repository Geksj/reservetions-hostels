package com.project.reservations_hotel.repository.specification;

import com.project.reservations_hotel.entity.Room;
import com.project.reservations_hotel.model.filter.RoomFilter;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public interface RoomSpecification {

    static Specification<Room> withFilter(RoomFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(filter.getId() != null) {
                predicates.add(cb.equal(root.get("id"), filter.getId()));
            }

            if(filter.getTitle() != null) {
                predicates.add(cb.equal(root.get("title"), filter.getTitle()));
            }

            if(filter.getMaxPeople() != null) {
                predicates.add(cb.equal(root.get("maxSize"), filter.getMaxPeople()));
            }

            if(filter.getMinPrice() != null && filter.getMaxPrice() != null) {
                predicates.add(cb.between(root.get("price"), filter.getMinPrice(), filter.getMaxPrice()));
            }

            if(filter.getMaxPrice() != null && filter.getMinPrice() == null) {
                predicates.add(cb.le(root.get("price"), filter.getMaxPrice()));
            }

            if(filter.getCheckInDate() != null && filter.getDepartureDate() != null) {
                LocalDate checkInDateLD = LocalDate.parse(filter.getCheckInDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                LocalDate departureDateLD = LocalDate.parse(filter.getDepartureDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                LocalDate current = checkInDateLD;

                while (current.isBefore(departureDateLD)) {
                    Predicate dateNotBooked = cb.not(cb.isMember(current, root.get("bookedDates")));

                    predicates.add(dateNotBooked);

                    current = current.plusDays(1);
                }
            }

            if(filter.getMaxPrice() == null && filter.getMinPrice() != null) {
                predicates.add(cb.ge(root.get("price"), filter.getMinPrice()));
            }

            return predicates.isEmpty() ? null : cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
