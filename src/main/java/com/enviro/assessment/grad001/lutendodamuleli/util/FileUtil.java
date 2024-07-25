package com.enviro.assessment.grad001.lutendodamuleli.util;

import com.enviro.assessment.grad001.lutendodamuleli.model.EnvironmentalData;
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

public class FileUtil {
    public static Set<EnvironmentalData> processFile(MultipartFile file) throws IOException {
        try(Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            HeaderColumnNameMappingStrategy<EnvironmentalData> strategy =
                    new HeaderColumnNameMappingStrategy<>();
            strategy.setType(EnvironmentalData.class);
            CsvToBean<EnvironmentalData> csvToBean =
                    new CsvToBeanBuilder<EnvironmentalData>(reader)
                            .withMappingStrategy(strategy)
                            .withIgnoreEmptyLine(true)
                            .withIgnoreLeadingWhiteSpace(true)
                            .build();

            return csvToBean.parse()
                    .stream()
                    .map(csvLine -> EnvironmentalData.builder()
                            .date(csvLine.getDate())
                            .location(csvLine.getLocation())
                            .temperature(csvLine.getTemperature())
                            .humidity(csvLine.getHumidity())
                            .build()
                    )
                    .collect(Collectors.toSet());
        }
    }

    public static void validateFile(MultipartFile file) {
        if(file.isEmpty())
            throw new IllegalStateException("File is empty");
        if(!file.getContentType().equals("text/plain"))
            throw new IllegalArgumentException("Only text files are allowed");
        if (file.getSize() > 10 * 1024 * 1024) // 10MB limit
            throw new IllegalArgumentException("File size exceeds the limit of 10MB");
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
