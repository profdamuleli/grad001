package com.enviro.assessment.grad001.lutendodamuleli.service;

import com.enviro.assessment.grad001.lutendodamuleli.model.Result;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FileProcessingService {

    public Set<Result> processFile(MultipartFile file) throws IOException {
        // Implement file processing logic here
        // Example: read, parse, validate, and save data

        try(Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            HeaderColumnNameMappingStrategy<Result> strategy =
                    new HeaderColumnNameMappingStrategy<>();
            strategy.setType(Result.class);
            CsvToBean<Result> csvToBean =
                    new CsvToBeanBuilder<Result>(reader)
                            .withMappingStrategy(strategy)
                            .withIgnoreEmptyLine(true)
                            .withIgnoreLeadingWhiteSpace(true)
                            .build();
            return csvToBean.parse()
                    .stream()
                    .map(csvLine -> Result.builder()
                            .date(csvLine.getDate())
                            .location(csvLine.getLocation())
                            .temperature(csvLine.getTemperature())
                            .humidity(csvLine.getHumidity())
                            .build()
                    )
                    .collect(Collectors.toSet());

        }
    }
}
