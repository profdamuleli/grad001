package com.enviro.assessment.grad001.lutendodamuleli.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ApiError {
    private final HttpStatus status;
    private final String message;
    private final LocalDateTime timestamp;

    public ApiError(HttpStatus status, String message, LocalDateTime timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
