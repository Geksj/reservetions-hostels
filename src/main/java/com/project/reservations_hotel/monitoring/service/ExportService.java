package com.project.reservations_hotel.monitoring.service;

import com.project.reservations_hotel.monitoring.ExportFile;
import com.project.reservations_hotel.monitoring.ExportFileFactory;
import com.project.reservations_hotel.monitoring.provider.ExportBookingProvider;
import com.project.reservations_hotel.monitoring.provider.ExportUserProvider;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExportService {

    private final ExportBookingProvider exportBookingProvider;

    private final ExportUserProvider exportUserProvider;

    public ExportService(ExportBookingProvider exportBookingProvider, ExportUserProvider exportUserProvider) {
        this.exportBookingProvider = exportBookingProvider;
        this.exportUserProvider = exportUserProvider;
    }

    public String export(Class<? extends ExportFile> type) {
        ExportFileFactory factory = new ExportFileFactory(List.of(exportUserProvider, exportBookingProvider));

        return factory.exportData(type).exportCSV();
    }

}
