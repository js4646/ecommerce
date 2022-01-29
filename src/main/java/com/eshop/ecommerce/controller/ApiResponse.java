package com.eshop.ecommerce.controller;

import java.time.LocalDateTime;

public class ApiResponse {

    private final boolean success;
    private final String message;

    public ApiResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSucces() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getTimeStamp() {
        return LocalDateTime.now().toString();
    }
}
