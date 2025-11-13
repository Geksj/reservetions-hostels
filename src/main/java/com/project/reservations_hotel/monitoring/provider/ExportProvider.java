package com.project.reservations_hotel.monitoring.provider;

import com.project.reservations_hotel.monitoring.ExportFile;

public interface ExportProvider {

    Class<? extends ExportFile> getType();

    ExportFile create();
}
