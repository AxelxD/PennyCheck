package com.compartamos.pennycheck.api;

import com.compartamos.pennycheck.dto.AccountValidatorConsumptionResponse;
import com.compartamos.pennycheck.dto.AccountValidatorRequest;
import com.compartamos.pennycheck.dto.AckAccountValidatorResponse;
import com.compartamos.pennycheck.service.PennyCheckService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/sandbox/v1/bavs")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Bank Account Verification API")
public class PennyCheckResource {

    @Inject
    PennyCheckService pennyCheckService;

    @POST
    @Path("/accountValidator")
    @Operation(summary = "Requests an account validator process")
    public Response createValidation(@HeaderParam("x-api-key") String apiKey,
                                     @Valid AccountValidatorRequest request) {
        validateApiKey(apiKey);
        AckAccountValidatorResponse response = pennyCheckService.createAccountValidator(request);
        return Response.ok(response).build();
    }

    @GET
    @Path("/accountValidator/{inquiryId}")
    @Operation(summary = "Returns the account validator process payload")
    public Response getValidation(@HeaderParam("x-api-key") String apiKey,
                                  @PathParam("inquiryId") String inquiryId) {
        validateApiKey(apiKey);
        AccountValidatorConsumptionResponse response = pennyCheckService.getAccountValidator(inquiryId);
        return Response.ok(response).build();
    }

    private void validateApiKey(String apiKey) {
        if (apiKey == null || apiKey.isBlank()) {
            throw new BadRequestException("Missign parameters {x-api-key}.");
        }
    }
}
