package com.enviro.assessment.grad001.lutendodamuleli.exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MultipartException.class)
    protected ResponseEntity<Object> handleMultipartException(MultipartException ex) {
        logger.error("Multipart request exception", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Multipart request exception: " + ex.getMessage());
    }

    @ExceptionHandler(IOException.class)
    protected ResponseEntity<Object> handleIOException(IOException ex) {
        logger.error("IOException occurred", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("IOException occurred: " + ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
        logger.error("Illegal argument exception", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Illegal argument exception: " + ex.getMessage());
    }

    @ExceptionHandler(IllegalStateException.class)
    protected ResponseEntity<Object> handleIllegalArgumentException(IllegalStateException ex) {
        logger.error("File is empty", ex);
        ApiError apiError = new ApiError(HttpStatus.NO_CONTENT, ex.getMessage(), LocalDateTime.now());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(FileNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(FileNotFoundException ex) {
        // Customize error response
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage(), LocalDateTime.now());
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
