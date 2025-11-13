package com.project.reservations_hotel.monitoring;

import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Slf4j
public class ExportCsv<T> {

    private final Class<T> type;

    public ExportCsv(Class<T> type) {
        this.type = type;
    }

    String createFile(List<T> dataList, File file) {
        try (Writer writer = new FileWriter(file)){
            HeaderColumnNameMappingStrategy<T> strategy = new HeaderColumnNameMappingStrategy<>();

            strategy.setType(type);

            StatefulBeanToCsv<T> beanToCsv = new StatefulBeanToCsvBuilder<T>(writer)
                    .withSeparator(';')
                    .withMappingStrategy(strategy)
                    .build();

            beanToCsv.write(dataList);
        } catch (IOException ex) {
            log.info("Writing error");
        } catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException ex) {
            log.info("Mismatch csv data or required field empty");
        }

        return file.getAbsolutePath();
    }

}
