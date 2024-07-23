package com.enviro.assessment.grad001.lutendodamuleli.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class EnvironmentalData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    @Lob
    private byte[] fileData;
}
