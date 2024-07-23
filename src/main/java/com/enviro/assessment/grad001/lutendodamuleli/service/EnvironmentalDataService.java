package com.enviro.assessment.grad001.lutendodamuleli.service;

import com.enviro.assessment.grad001.lutendodamuleli.model.EnvironmentalData;
import com.enviro.assessment.grad001.lutendodamuleli.model.Result;
import com.enviro.assessment.grad001.lutendodamuleli.repository.EnvironmentalDataRepository;
import com.enviro.assessment.grad001.lutendodamuleli.util.ConvertionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EnvironmentalDataService {
    @Autowired
    private EnvironmentalDataRepository environmentalDataRepository;

    public void processAndSaveData(String fileName, byte[] fileData) {
        EnvironmentalData data = new EnvironmentalData();
        data.setFileName(fileName);
        data.setFileData(fileData);

        environmentalDataRepository.save(data);
    }

    public Set<Result> getResultById(Long id) {
        Optional<EnvironmentalData> byId = environmentalDataRepository.findById(id);
        try {
            MultipartFile file = ConvertionUtil.convertByteIntoFile(byId.get().getFileData(), byId.get().getFileName());
            return ConvertionUtil.processFile(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<EnvironmentalData> getAllData() {
        return environmentalDataRepository.findAll();
    }
}
