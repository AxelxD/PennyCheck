package com.compartamos.pennycheck.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class AccountValidatorDto {

    @NotBlank(message = "externalId es obligatorio")
    @Pattern(regexp = "^[0-9a-fA-F-]{36}$", message = "externalId debe ser un UUID valido")
    public String externalId;

    @NotBlank(message = "subscriptionId es obligatorio")
    @Pattern(regexp = "^[0-9a-fA-F-]{36}$", message = "subscriptionId debe ser un UUID valido")
    public String subscriptionId;

    @NotNull(message = "bankId es obligatorio")
    @Min(value = 1000, message = "bankId debe ser mayor o igual a 1000")
    @Max(value = 99999, message = "bankId debe ser menor o igual a 99999")
    public Integer bankId;

    @NotBlank(message = "accountType es obligatorio")
    @Pattern(regexp = "^(1|2)$", message = "accountType debe ser 1 o 2")
    public String accountType;

    @NotBlank(message = "identifierType es obligatorio")
    @Pattern(regexp = "^(CLABE|AccountNumber|PhoneNumber)$", message = "identifierType debe ser CLABE, AccountNumber o PhoneNumber")
    public String identifierType;

    @NotBlank(message = "accountIdentifier es obligatorio")
    @Pattern(regexp = "^[0-9]{10,18}$", message = "accountIdentifier debe tener entre 10 y 18 digitos")
    public String accountIdentifier;

    @NotBlank(message = "name es obligatorio")
    @Size(min = 1, max = 100, message = "name debe tener entre 1 y 100 caracteres")
    public String name;

    @Size(min = 1, max = 100, message = "secondName debe tener entre 1 y 100 caracteres")
    public String secondName;

    @Size(min = 1, max = 100, message = "lastName debe tener entre 1 y 100 caracteres")
    public String lastName;

    @Size(min = 1, max = 100, message = "secondLastName debe tener entre 1 y 100 caracteres")
    public String secondLastName;

    @Pattern(regexp = "^[A-Za-z0-9]{18}$", message = "citizenId debe tener 18 caracteres alfanumericos")
    public String citizenId;

    @Pattern(regexp = "^[A-Za-z0-9]{10,13}$", message = "taxPayerIdentificationNumber debe tener entre 10 y 13 caracteres alfanumericos")
    public String taxPayerIdentificationNumber;

    @NotBlank(message = "verifierCompany es obligatorio")
    @Pattern(regexp = "^[A-Za-z0-9]{1,14}$", message = "verifierCompany debe ser alfanumerico y tener entre 1 y 14 caracteres")
    public String verifierCompany;
}
