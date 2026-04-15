package com.compartamos.pennycheck.dto;

public class ErrorDetail {
    public String code;
    public String message;

    public ErrorDetail() {
    }

    public ErrorDetail(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
