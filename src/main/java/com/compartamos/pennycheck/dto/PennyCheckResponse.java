package com.compartamos.pennycheck.dto;

public class PennyCheckResponse {
    public String correlationId;
    public String status;
    public String message;
    public String externalReference;

    public PennyCheckResponse() {
    }

    public PennyCheckResponse(String correlationId, String status, String message, String externalReference) {
        this.correlationId = correlationId;
        this.status = status;
        this.message = message;
        this.externalReference = externalReference;
    }
}
