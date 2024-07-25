package com.enviro.assessment.grad001.lutendodamuleli.service;

import com.enviro.assessment.grad001.lutendodamuleli.model.FileInformation;
import com.enviro.assessment.grad001.lutendodamuleli.model.EnvironmentalData;
import com.enviro.assessment.grad001.lutendodamuleli.repository.EnvironmentalDataRepository;
import com.enviro.assessment.grad001.lutendodamuleli.repository.FileInformationRepository;
import com.enviro.assessment.grad001.lutendodamuleli.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
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

        if(isFileExists(fileName)){
            throw new IllegalArgumentException("File with name " + fileName + " already exists");
        }
        FileUtil.validateFile(file);
        FileInformation fileInformation = new FileInformation();
        fileInformation.setFileName(fileName);
        fileInformation.setFileData(fileData);

        Set<EnvironmentalData> environmentalData = null;
        try {
            environmentalData = processFile(file);
            if(environmentalData.isEmpty()){
                throw new IllegalStateException("File is Empty");
            }
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

    public Set<EnvironmentalData> getResultById(Long id) throws FileNotFoundException {
        try {
            Optional<FileInformation> byId = fileUploadRepository.findById(id);
            if (byId.isPresent()) {
                return byId.get().getEnvironmentalData();
            } else {
                throw new FileNotFoundException("File not found with id: " + id);
            }
        } catch (NoSuchElementException e) {
            throw new FileNotFoundException("File not found with id: " + id);
        }
    }

    public List<FileInformation> getAllData() {
        return fileUploadRepository.findAll();
    }
    public boolean isFileExists(String fileName) {
        FileInformation existingFile = fileUploadRepository.findByFileName(fileName);
        return existingFile != null;
    }
}
