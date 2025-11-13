package com.project.reservations_hotel.monitoring.provider;

import com.project.reservations_hotel.monitoring.ExportBooking;
import com.project.reservations_hotel.monitoring.ExportFile;
import com.project.reservations_hotel.monitoring.repository.BookingStatRepository;
import org.springframework.stereotype.Component;

@Component
public class ExportBookingProvider implements ExportProvider{

    private final BookingStatRepository bookingStatRepository;

    public ExportBookingProvider(BookingStatRepository bookingStatRepository) {
        this.bookingStatRepository = bookingStatRepository;
    }

    @Override
    public Class<? extends ExportFile> getType() {
        return ExportBooking.class;
    }

    @Override
    public ExportFile create() {
        return new ExportBooking(bookingStatRepository);
    }
}
