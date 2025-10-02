package com.project.reservations_hotel.repository;

import com.project.reservations_hotel.entity.eventsEntity.BookingStatData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingStatRepository extends MongoRepository<BookingStatData, String> {
}
