package com.compartamos.pennycheck.api;

import com.compartamos.pennycheck.dto.PennyCheckRequest;
import com.compartamos.pennycheck.dto.PennyCheckResponse;
import com.compartamos.pennycheck.service.PennyCheckService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/api/v1/pennycheck")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "PennyCheck")
public class PennyCheckResource {

    @Inject
    PennyCheckService pennyCheckService;

    @POST
    @Path("/validations")
    @Operation(summary = "Recibe una solicitud de validación bancaria")
    public Response createValidation(@HeaderParam("X-Correlation-Id") String correlationId,
                                     @Valid PennyCheckRequest request) {
        PennyCheckResponse response = pennyCheckService.processValidation(correlationId, request);
        return Response.accepted(response).build();
    }
}
