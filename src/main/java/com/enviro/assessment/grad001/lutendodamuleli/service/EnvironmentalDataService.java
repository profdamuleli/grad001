package com.enviro.assessment.grad001.lutendodamuleli.service;

import com.enviro.assessment.grad001.lutendodamuleli.model.EnvironmentalData;
import com.enviro.assessment.grad001.lutendodamuleli.model.Result;
import com.enviro.assessment.grad001.lutendodamuleli.repository.EnvironmentalDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnvironmentalDataService {
    @Autowired
    private EnvironmentalDataRepository environmentalDataRepository;
    @Autowired
    private FileProcessingService fileProcessingService;

    public void processAndSaveData(String fileName, byte[] fileData) {
        EnvironmentalData data = new EnvironmentalData();
        data.setFileName(fileName);
        data.setFileData(fileData);

        // Additional processing if needed

        environmentalDataRepository.save(data);
    }

    public Result getResultById(Long id) {
        Optional<EnvironmentalData> byId = environmentalDataRepository.findById(id);
        //fileProcessingService.processFile()
        return new Result();
    }

    public List<EnvironmentalData> getAllData() {
        return environmentalDataRepository.findAll();
    }
}
