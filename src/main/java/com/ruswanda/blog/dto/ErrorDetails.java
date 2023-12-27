package com.ruswanda.blog.dto;

import java.time.LocalDateTime;

public class ErrorDetails {

    private LocalDateTime time = LocalDateTime.now();
    private String message;
    private String details;

    public ErrorDetails(LocalDateTime time, String message, String details) {
        this.time = time;
        this.message = message;
        this.details = details;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}