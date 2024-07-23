package com.enviro.assessment.grad001.lutendodamuleli.model;

import com.opencsv.bean.CsvBindByName;
import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Result {
    @CsvBindByName(column = "Date")
    private String date;
    @CsvBindByName(column = "Location")
    private String location;
    @CsvBindByName(column = "Temperature")
    private Integer temperature;
    @CsvBindByName(column = "Humidity")
    private Integer humidity;
}
