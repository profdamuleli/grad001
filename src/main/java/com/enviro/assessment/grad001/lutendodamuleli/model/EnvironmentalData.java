package com.enviro.assessment.grad001.lutendodamuleli.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.opencsv.bean.CsvBindByName;
import jakarta.persistence.*;
import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class EnvironmentalData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CsvBindByName(column = "Date")
    private String date;
    @CsvBindByName(column = "Location")
    private String location;
    @CsvBindByName(column = "Temperature")
    private Integer temperature;
    @CsvBindByName(column = "Humidity")
    private Integer humidity;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_information_id")
    private FileInformation fileInformation;
}
