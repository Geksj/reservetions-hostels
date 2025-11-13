package com.project.reservations_hotel.monitoring;

import com.project.reservations_hotel.monitoring.entity.BookingMonitoringData;
import com.project.reservations_hotel.monitoring.repository.BookingStatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@Slf4j
@RequiredArgsConstructor
public class ExportBooking implements ExportFile{

    private final BookingStatRepository bookingStatRepository;

    @Override
    public String exportCSV() {
        ExportCsv<BookingMonitoringData> exportCsv = new ExportCsv<>(BookingMonitoringData.class);
        File file = new File("statisticsByBooking.csv");

        return exportCsv.createFile(bookingStatRepository.findAll(), file);
    }
}
