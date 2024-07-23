package com.enviro.assessment.grad001.lutendodamuleli.util;

import com.enviro.assessment.grad001.lutendodamuleli.model.Result;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;

public class ConvertionUtil {
    public static Set<Result> processFile(MultipartFile file) throws IOException {
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

    public static MultipartFile convertByteIntoFile(byte[] bytes, String fileName) throws IOException {
        // Create a temporary file
        Path tempFile = Files.createTempFile(fileName, "");

        // Write the bytes to the temporary file
        Files.write(tempFile, bytes);

        // Create and return a MultipartFile from the temporary file
        return new MockMultipartFile(fileName, fileName, null, Files.readAllBytes(tempFile));
    }
}
