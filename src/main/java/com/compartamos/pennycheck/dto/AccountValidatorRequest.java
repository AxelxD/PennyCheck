package com.compartamos.pennycheck.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class AccountValidatorRequest {

    @NotNull(message = "accountValidator es obligatorio")
    @Valid
    public AccountValidatorDto accountValidator;
}
