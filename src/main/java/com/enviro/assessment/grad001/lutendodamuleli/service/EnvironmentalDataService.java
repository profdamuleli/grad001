package com.enviro.assessment.grad001.lutendodamuleli.service;

import com.enviro.assessment.grad001.lutendodamuleli.model.FileInformation;
import com.enviro.assessment.grad001.lutendodamuleli.model.EnvironmentalData;
import com.enviro.assessment.grad001.lutendodamuleli.repository.EnvironmentalDataRepository;
import com.enviro.assessment.grad001.lutendodamuleli.repository.FileInformationRepository;
import com.enviro.assessment.grad001.lutendodamuleli.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.enviro.assessment.grad001.lutendodamuleli.util.FileUtil.*;

@Service
public class EnvironmentalDataService {
    @Autowired
    private FileInformationRepository fileUploadRepository;
    @Autowired
    private EnvironmentalDataRepository environmentalDataRepository;

    public void processAndSaveData(MultipartFile file, String fileName, byte[] fileData) {
        FileUtil.validateFile(file);
        FileInformation fileInformation = new FileInformation();
        fileInformation.setFileName(fileName);
        fileInformation.setFileData(fileData);

        Set<EnvironmentalData> environmentalData = null;
        try {
            environmentalData = processFile(file);
        } catch (IOException e) {
            throw new RuntimeException("Failed to process file: " + e.getMessage());
        }
        // Link EnvironmentalData with FileInformation
        for (EnvironmentalData data : environmentalData) {
            data.setFileInformation(fileInformation);
        }
        fileInformation.setEnvironmentalData(environmentalData);

        // Save FileInformation and associated EnvironmentalData
        fileUploadRepository.save(fileInformation);
    }

    public Set<EnvironmentalData> getResultById(Long id) {
        Optional<FileInformation> byId = fileUploadRepository.findById(id);
        return byId.get().getEnvironmentalData();
    }

    public List<FileInformation> getAllData() {
        return fileUploadRepository.findAll();
    }
}
