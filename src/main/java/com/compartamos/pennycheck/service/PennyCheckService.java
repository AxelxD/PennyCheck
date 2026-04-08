package com.compartamos.pennycheck.service;

import com.compartamos.pennycheck.dto.PennyCheckRequest;
import com.compartamos.pennycheck.dto.PennyCheckResponse;
import com.compartamos.pennycheck.mapper.PennyCheckMapper;
import com.compartamos.pennycheck.model.CircleCreditRequest;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.UUID;

@ApplicationScoped
public class PennyCheckService {

    @Inject
    PennyCheckMapper pennyCheckMapper;

    public PennyCheckResponse processValidation(String correlationId, PennyCheckRequest request) {
        String resolvedCorrelationId = resolveCorrelationId(correlationId);

        CircleCreditRequest externalRequest = pennyCheckMapper.toCircleCreditRequest(request);

        // TODO: Invocar CircleCreditClient con el contrato real
        return new PennyCheckResponse(
                resolvedCorrelationId,
                "RECEIVED",
                "Solicitud recibida correctamente",
                "EXT-" + UUID.randomUUID()
        );
    }

    private String resolveCorrelationId(String correlationId) {
        return correlationId == null || correlationId.isBlank()
                ? UUID.randomUUID().toString()
                : correlationId;
    }
}
