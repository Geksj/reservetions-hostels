package com.project.reservations_hotel.monitoring;

import com.project.reservations_hotel.monitoring.entity.UserMonitoringData;
import com.project.reservations_hotel.monitoring.repository.UserStatRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@Slf4j
public class ExportUser implements ExportFile{

    private final UserStatRepository userStatRepository;

    public ExportUser(UserStatRepository userStatRepository) {
        this.userStatRepository = userStatRepository;
    }

    @Override
    public String exportCSV() {
        File file = new File("statisticsByUser.csv");
        ExportCsv<UserMonitoringData> exportCsv = new ExportCsv<>(UserMonitoringData.class);

        return exportCsv.createFile(userStatRepository.findAll(), file);
    }
}
