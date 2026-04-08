package com.compartamos.pennycheck.service;

import com.compartamos.pennycheck.dto.WebhookRequest;
import com.compartamos.pennycheck.dto.WebhookResponse;
import com.compartamos.pennycheck.exception.UnauthorizedWebhookException;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class WebhookService {

    @ConfigProperty(name = "pennycheck.webhook.token")
    String configuredToken;

    public WebhookResponse processWebhook(String token, WebhookRequest request) {
        if (token == null || !token.equals(configuredToken)) {
            throw new UnauthorizedWebhookException("Token de webhook inválido");
        }

        // TODO: Agregar correlación, idempotencia y persistencia
        return new WebhookResponse("OK", "Webhook procesado correctamente");
    }
}
