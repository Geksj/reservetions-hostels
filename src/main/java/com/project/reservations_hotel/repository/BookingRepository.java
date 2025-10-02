package com.project.reservations_hotel.repository;

import com.project.reservations_hotel.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
