package com.project.reservations_hotel.monitoring.service;

import com.project.reservations_hotel.monitoring.entity.BookingMonitoringData;
import com.project.reservations_hotel.monitoring.entity.UserMonitoringData;
import com.project.reservations_hotel.model.kafka.StatEvent;
import com.project.reservations_hotel.monitoring.repository.BookingStatRepository;
import com.project.reservations_hotel.monitoring.repository.UserStatRepository;
import com.project.reservations_hotel.utils.AppDateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MonitoringService {

    private final UserStatRepository userStatRepository;

    private final BookingStatRepository bookingStatRepository;

    public void save(StatEvent event) {
        if(event.getCheckInDate() == null && event.getDepartureDate() == null) {
            UserMonitoringData user = new UserMonitoringData();

            user.setUserId(event.getUserId());
            user.setCreatedDate(AppDateUtils.parseInstantToLD(
                    event.getCreatedDate()
            ));

            userStatRepository.save(user);

            log.info("User saved. userId: {}", event.getUserId());

            return;
        }

        BookingMonitoringData stat = new BookingMonitoringData();

        stat.setUserId(event.getUserId());
        stat.setCheckInDate(event.getCheckInDate());
        stat.setDepartureDate(event.getDepartureDate());

        bookingStatRepository.save(stat);
    }

}
