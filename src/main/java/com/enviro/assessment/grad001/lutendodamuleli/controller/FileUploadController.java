package com.enviro.assessment.grad001.lutendodamuleli.controller;

import com.enviro.assessment.grad001.lutendodamuleli.model.FileInformation;
import com.enviro.assessment.grad001.lutendodamuleli.model.EnvironmentalData;
import com.enviro.assessment.grad001.lutendodamuleli.service.EnvironmentalDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class FileUploadController {
    @Autowired
    private EnvironmentalDataService environmentalDataService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            byte[] fileData = file.getBytes();

            environmentalDataService.processAndSaveData(file, fileName, fileData);

            return ResponseEntity.ok("File uploaded successfully: " + fileName);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload file: " + file.getOriginalFilename());
        }
    }

    @GetMapping("/results/{id}")
    public ResponseEntity<Set<EnvironmentalData>> getProcessedData(@PathVariable Long id) {
        Set<EnvironmentalData> result = environmentalDataService.getResultById(id);
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/results")
    public ResponseEntity<List<FileInformation>> getAllData() {
        List<FileInformation> data = environmentalDataService.getAllData();
        return ResponseEntity.ok(data);
    }
}
