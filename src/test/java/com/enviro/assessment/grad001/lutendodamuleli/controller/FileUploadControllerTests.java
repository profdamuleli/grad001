package com.enviro.assessment.grad001.lutendodamuleli.controller;

import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import com.enviro.assessment.grad001.lutendodamuleli.service.EnvironmentalDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.reset;

@WebMvcTest(controllers = FileUploadController.class)
@AutoConfigureMockMvc
public class FileUploadControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EnvironmentalDataService environmentalDataService;

    @BeforeEach
    void setUp() {
        // Reset mocks before each test
        reset(environmentalDataService);
    }

    @Test
    void testUploadFileSuccess() throws Exception {
        MockMultipartFile file = new MockMultipartFile(
                "file", "test.csv", "text/csv", "some,csv,data".getBytes());
    }

    @Test
    void testUploadFileFailure() throws Exception {
        MockMultipartFile file = new MockMultipartFile(
                "file", "test.csv", "text/csv", "some,csv,data".getBytes());

    }

    @Test
    void testUploadInvalidFile() throws Exception {
        MockMultipartFile file = new MockMultipartFile(
                "file", "test.txt", "text/plain", "invalid file".getBytes());

    }
}
