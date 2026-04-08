package com.compartamos.pennycheck.dto;

import jakarta.validation.constraints.NotBlank;

public class WebhookRequest {
    @NotBlank(message = "externalReference es obligatorio")
    public String externalReference;

    @NotBlank(message = "resultCode es obligatorio")
    public String resultCode;

    @NotBlank(message = "resultMessage es obligatorio")
    public String resultMessage;
}
