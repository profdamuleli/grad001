package com.enviro.assessment.grad001.lutendodamuleli.service;

import com.enviro.assessment.grad001.lutendodamuleli.model.EnvironmentalData;
import com.enviro.assessment.grad001.lutendodamuleli.repository.EnvironmentalDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnvironmentalDataService {
    @Autowired
    private EnvironmentalDataRepository environmentalDataRepository;

    public void processAndSaveData(String fileName, byte[] fileData) {
        EnvironmentalData data = new EnvironmentalData();
        data.setFileName(fileName);
        data.setFileData(fileData);

        // Additional processing if needed

        environmentalDataRepository.save(data);
    }

    public List<EnvironmentalData> getAllData() {
        return environmentalDataRepository.findAll();
    }
}
