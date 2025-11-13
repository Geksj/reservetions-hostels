package com.project.reservations_hotel.monitoring.provider;

import com.project.reservations_hotel.monitoring.ExportFile;
import com.project.reservations_hotel.monitoring.ExportUser;
import com.project.reservations_hotel.monitoring.repository.UserStatRepository;
import org.springframework.stereotype.Component;

@Component
public class ExportUserProvider implements ExportProvider {

    private final UserStatRepository userStatRepository;

    public ExportUserProvider(UserStatRepository userStatRepository) {
        this.userStatRepository = userStatRepository;
    }

    @Override
    public Class<? extends ExportFile> getType() {
        return ExportUser.class;
    }

    @Override
    public ExportFile create() {
        return new ExportUser(userStatRepository);
    }
}
