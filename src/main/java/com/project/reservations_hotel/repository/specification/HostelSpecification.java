package com.project.reservations_hotel.repository.specification;

import com.project.reservations_hotel.entity.Hostel;
import com.project.reservations_hotel.model.filter.HostelFilter;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public interface HostelSpecification {

    static Specification<Hostel> withFilter(HostelFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(filter.getCity() != null) {
                predicates.add(cb.equal(root.get("address").get("city"), filter.getCity()));
            }

            if(filter.getRating() != null) {
                predicates.add(cb.ge(root.get("rating").get("rating"), filter.getRating()));
            }

            if(filter.getId() != null) {
                predicates.add(cb.equal(root.get("id"), filter.getId()));
            }

            if(filter.getAddress() != null) {
                predicates.add(cb.equal(root.get("address").get("street"), filter.getAddress()));
            }

            if(filter.getAdTitle() != null) {
                predicates.add(cb.equal(root.get("adTitle"), filter.getAdTitle()));
            }

            if(filter.getTitle() != null) {
                predicates.add(cb.equal(root.get("title"), filter.getAdTitle()));
            }

            if(filter.getDistanceFromCenter() != null) {
                predicates.add(cb.le(root.get("distanceFromCenter"), filter.getDistanceFromCenter()));
            }

            if(filter.getRatingCount() != null) {
                predicates.add(cb.ge(root.get("rating").get("ratingCount"), filter.getRatingCount()));
            }

            return predicates.isEmpty() ? null : cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
