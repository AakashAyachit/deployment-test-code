package com.teleconnect.response;

public class RegistrationUserResponse {

    private String status;
    private String message;

    // Default constructor
    public RegistrationUserResponse() {
    }

    // Constructor with parameters
    public RegistrationUserResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    // Getters and setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
