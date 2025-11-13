package com.project.reservations_hotel.monitoring.repository;

import com.project.reservations_hotel.monitoring.entity.UserMonitoringData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStatRepository extends MongoRepository<UserMonitoringData, String> {
}
