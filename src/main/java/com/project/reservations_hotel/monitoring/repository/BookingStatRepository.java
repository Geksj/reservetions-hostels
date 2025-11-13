package com.project.reservations_hotel.monitoring.repository;

import com.project.reservations_hotel.monitoring.entity.BookingMonitoringData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingStatRepository extends MongoRepository<BookingMonitoringData, String> {
}
