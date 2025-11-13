package com.project.reservations_hotel.monitoring;

import com.project.reservations_hotel.monitoring.provider.ExportProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ExportFileFactory {

    Map<Class<? extends ExportFile>, ExportProvider> providers;

    public ExportFileFactory(List<ExportProvider> providers) {
        this.providers = providers.stream()
                .collect(Collectors.toMap(
                        ExportProvider::getType,
                        Function.identity()
                ));
    }

    public ExportFile exportData(Class<? extends  ExportFile> type) {
        ExportProvider provider = providers.get(type);

        if(provider == null) {
            log.info("Provider {} is null", type.getSimpleName());
            return null;
        }

        return provider.create();
    }
}
