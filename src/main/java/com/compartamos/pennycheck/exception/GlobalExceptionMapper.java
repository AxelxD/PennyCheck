package com.compartamos.pennycheck.exception;

import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        Map<String, Object> body = new HashMap<>();

        if (exception instanceof ConstraintViolationException validationException) {
            body.put("code", "VALIDATION_ERROR");
            body.put("message", validationException.getConstraintViolations()
                    .stream()
                    .map(v -> v.getMessage())
                    .collect(Collectors.joining(", ")));
            return Response.status(Response.Status.BAD_REQUEST)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(body)
                    .build();
        }

        if (exception instanceof UnauthorizedWebhookException) {
            body.put("code", "UNAUTHORIZED_WEBHOOK");
            body.put("message", exception.getMessage());
            return Response.status(Response.Status.UNAUTHORIZED)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(body)
                    .build();
        }

        body.put("code", "INTERNAL_ERROR");
        body.put("message", exception.getMessage() == null ? "Error interno" : exception.getMessage());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .type(MediaType.APPLICATION_JSON)
                .entity(body)
                .build();
    }
}
