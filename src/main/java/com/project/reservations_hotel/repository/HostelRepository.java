package com.project.reservations_hotel.repository;

import com.project.reservations_hotel.entity.Hostel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HostelRepository extends JpaRepository<Hostel, Long>, JpaSpecificationExecutor<Hostel> {

    boolean existsByTitle(String title);
}
