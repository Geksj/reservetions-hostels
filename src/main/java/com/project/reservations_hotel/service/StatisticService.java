package com.project.reservations_hotel.service;

import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.project.reservations_hotel.entity.eventsEntity.BookingStatData;
import com.project.reservations_hotel.entity.eventsEntity.UserStatData;
import com.project.reservations_hotel.model.kafka.StatEvent;
import com.project.reservations_hotel.repository.BookingStatRepository;
import com.project.reservations_hotel.repository.UserStatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatisticService {

    private final UserStatRepository userStatRepository;

    private final BookingStatRepository bookingStatRepository;

    public void save(StatEvent event) {
        if(event.getCheckInDate() == null && event.getDepartureDate() == null) {
            UserStatData user = new UserStatData();

            user.setUserId(event.getUserId());
            user.setCreatedDate(event.getCreatedDate().atZone(ZoneId.systemDefault())
                    .toLocalDate());

            userStatRepository.save(user);
            log.info("User saved. userId: {}", event.getUserId());

            return;
        }

        BookingStatData stat = new BookingStatData();

        stat.setUserId(event.getUserId());
        stat.setCheckInDate(event.getCheckInDate());
        stat.setDepartureDate(event.getDepartureDate());

        bookingStatRepository.save(stat);
    }

    public String downloadUserStats() {
        List<?> statDataList = userStatRepository.findAll();

        File file = new File("statisticsByUser.csv");

        try (Writer writer = new FileWriter(file)){
            HeaderColumnNameMappingStrategy<UserStatData> strategy = new HeaderColumnNameMappingStrategy<>();

            strategy.setType(UserStatData.class);

            StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer)
                    .withSeparator(';')
                    .withMappingStrategy(strategy)
                    .build();

            beanToCsv.write(statDataList);
        } catch (Exception e) {
            log.info("Data failed parsing in csv file. Message: {}", e.getMessage());
        }

        return file.getAbsolutePath();
    }

    public String downloadBookingStats() {
        List<?> statDataList = bookingStatRepository.findAll();

        File file = new File("statisticsByBookibg.csv");

        try (Writer writer = new FileWriter(file)){
            HeaderColumnNameMappingStrategy<BookingStatData> strategy = new HeaderColumnNameMappingStrategy<>();

            strategy.setType(BookingStatData.class);

            StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer)
                    .withSeparator(';')
                    .withMappingStrategy(strategy)
                    .build();

            beanToCsv.write(statDataList);
        } catch (Exception e) {
            log.info("Data failed parsing in csv file. Message: {}", e.getMessage());
        }

        return file.getAbsolutePath();
    }




}
