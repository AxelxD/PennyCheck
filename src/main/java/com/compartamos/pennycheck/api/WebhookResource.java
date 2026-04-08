package com.compartamos.pennycheck.api;

import com.compartamos.pennycheck.dto.WebhookRequest;
import com.compartamos.pennycheck.dto.WebhookResponse;
import com.compartamos.pennycheck.service.WebhookService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/api/v1/pennycheck/webhook")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "PennyCheck Webhook")
public class WebhookResource {

    @Inject
    WebhookService webhookService;

    @POST
    @Operation(summary = "Recibe el callback de Círculo de Crédito")
    public Response receiveWebhook(@HeaderParam("X-Webhook-Token") String token,
                                   @Valid WebhookRequest request) {
        WebhookResponse response = webhookService.processWebhook(token, request);
        return Response.ok(response).build();
    }
}
