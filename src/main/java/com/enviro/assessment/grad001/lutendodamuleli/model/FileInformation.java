package com.enviro.assessment.grad001.lutendodamuleli.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
public class FileInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    @Lob
    private byte[] fileData;

    @OneToMany(mappedBy = "fileInformation", cascade = CascadeType.ALL)
    private Set<EnvironmentalData> environmentalData = new HashSet<>();

    public Long getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public Set<EnvironmentalData> getEnvironmentalData() {
        return environmentalData;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    public void setEnvironmentalData(Set<EnvironmentalData> environmentalData) {
        this.environmentalData = environmentalData;
    }
}
