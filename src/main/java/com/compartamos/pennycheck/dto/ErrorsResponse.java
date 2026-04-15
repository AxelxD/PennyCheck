package com.compartamos.pennycheck.dto;

import java.util.List;

public class ErrorsResponse {
    public List<ErrorDetail> errors;

    public ErrorsResponse() {
    }

    public ErrorsResponse(List<ErrorDetail> errors) {
        this.errors = errors;
    }
}
