package com.compartamos.pennycheck.service;

import com.compartamos.pennycheck.dto.AccountValidatorConsumptionResponse;
import com.compartamos.pennycheck.dto.AccountValidatorDto;
import com.compartamos.pennycheck.dto.AccountValidatorRequest;
import com.compartamos.pennycheck.dto.AckAccountValidatorResponse;
import com.compartamos.pennycheck.dto.ConsumptionResult;
import com.compartamos.pennycheck.dto.ReasonProcess;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;

import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@ApplicationScoped
public class PennyCheckService {

    public AckAccountValidatorResponse createAccountValidator(AccountValidatorRequest request) {
        AckAccountValidatorResponse response = new AckAccountValidatorResponse();
        response.acknowledgeId = UUID.randomUUID().toString();
        response.dateTime = OffsetDateTime.now(ZoneOffset.UTC).toString();
        response.operation = "request";
        response.message = "The bank account verification request has been received.";
        response.externalId = request.accountValidator.externalId;
        response.subscriptionId = request.accountValidator.subscriptionId;
        response.inquiryId = UUID.randomUUID().toString();
        return response;
    }

    public AccountValidatorConsumptionResponse getAccountValidator(String inquiryId) {
        if ("not-found".equalsIgnoreCase(inquiryId)) {
            throw new NotFoundException("Inquiry not found.");
        }

        AccountValidatorConsumptionResponse response = new AccountValidatorConsumptionResponse();
        response.acknowledgeId = UUID.randomUUID().toString();
        response.dateTime = OffsetDateTime.now(ZoneOffset.UTC).toString();
        response.operation = "consumption";
        response.message = "The bank account verification process has been completed.";

        ConsumptionResult result = new ConsumptionResult();
        result.reasonProcess = buildSuccessReason();
        result.request = buildSampleRequest(inquiryId);
        result.verificationConfidence = 0.76d;
        result.ownershipConfidence = 0.99d;
        result.cep = "https://www.banxico.org.mx/cep/go";
        response.result = result;
        return response;
    }

    private ReasonProcess buildSuccessReason() {
        ReasonProcess reasonProcess = new ReasonProcess();
        reasonProcess.code = "200.10";
        reasonProcess.descriptionError = "000";
        reasonProcess.message = "Received, paid and the return to the account was generated.";
        return reasonProcess;
    }

    private AccountValidatorDto buildSampleRequest(String inquiryId) {
        AccountValidatorDto request = new AccountValidatorDto();
        request.externalId = UUID.nameUUIDFromBytes(("external-" + inquiryId).getBytes(StandardCharsets.UTF_8)).toString();
        request.subscriptionId = UUID.nameUUIDFromBytes(("subscription-" + inquiryId).getBytes(StandardCharsets.UTF_8)).toString();
        request.bankId = 40012;
        request.accountType = "1";
        request.identifierType = "CLABE";
        request.accountIdentifier = "123456789012345678";
        request.name = "Juan";
        request.secondName = "Pruebas";
        request.lastName = "Ocho";
        request.secondLastName = "Seis";
        request.citizenId = "SSSS010101SSSSXXXX";
        request.taxPayerIdentificationNumber = "SSSS010101XXX";
        request.verifierCompany = "Empresa1";
        return request;
    }
}
