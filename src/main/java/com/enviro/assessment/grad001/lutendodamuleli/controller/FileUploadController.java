package com.enviro.assessment.grad001.lutendodamuleli.controller;

import com.enviro.assessment.grad001.lutendodamuleli.model.EnvironmentalData;
import com.enviro.assessment.grad001.lutendodamuleli.model.Result;
import com.enviro.assessment.grad001.lutendodamuleli.service.EnvironmentalDataService;
import com.enviro.assessment.grad001.lutendodamuleli.service.FileProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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

            environmentalDataService.processAndSaveData(fileName, fileData);

            return ResponseEntity.ok("File uploaded successfully: " + fileName);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload file: " + file.getOriginalFilename());
        }
    }

    @GetMapping("/results/{id}")
    public ResponseEntity<Result> getProcessedData(@PathVariable Long id) {
        Result result = environmentalDataService.getResultById(id);
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/results")
    public ResponseEntity<List<EnvironmentalData>> getAllData() {
        List<EnvironmentalData> data = environmentalDataService.getAllData();
        return ResponseEntity.ok(data);
    }
}
