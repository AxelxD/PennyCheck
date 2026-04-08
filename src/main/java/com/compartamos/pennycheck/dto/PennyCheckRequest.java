package com.compartamos.pennycheck.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

public class PennyCheckRequest {

    @NotBlank(message = "accountNumber es obligatorio")
    public String accountNumber;

    @NotBlank(message = "bankCode es obligatorio")
    @Pattern(regexp = "\\d{3}", message = "bankCode debe tener 3 dígitos")
    public String bankCode;

    @NotBlank(message = "accountType es obligatorio")
    public String accountType;

    @NotBlank(message = "customerId es obligatorio")
    public String customerId;

    @NotNull(message = "requestDate es obligatorio")
    public LocalDateTime requestDate;
}
