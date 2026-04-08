package com.compartamos.pennycheck.dto;

public class WebhookResponse {
    public String status;
    public String message;

    public WebhookResponse() {
    }

    public WebhookResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
