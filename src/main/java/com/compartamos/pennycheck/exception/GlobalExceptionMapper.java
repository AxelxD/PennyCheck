package com.compartamos.pennycheck.exception;

import com.compartamos.pennycheck.dto.ErrorDetail;
import com.compartamos.pennycheck.dto.ErrorsResponse;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.List;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        if (exception instanceof ConstraintViolationException validationException) {
            String message = validationException.getConstraintViolations()
                    .stream()
                    .map(v -> v.getMessage())
                    .sorted()
                    .reduce((left, right) -> left + ", " + right)
                    .orElse("Missign parameters {fields name}");
            return buildError(Response.Status.BAD_REQUEST, "400", message);
        }

        if (exception instanceof BadRequestException) {
            return buildError(Response.Status.BAD_REQUEST, "400", defaultMessage(exception, "Bad request."));
        }

        if (exception instanceof UnauthorizedWebhookException) {
            return buildError(Response.Status.UNAUTHORIZED, "401.4", defaultMessage(exception, "Unauthorized."));
        }

        if (exception instanceof NotFoundException) {
            return buildError(Response.Status.NOT_FOUND, "404.1", defaultMessage(exception, "Inquiry not found."));
        }

        return buildError(Response.Status.INTERNAL_SERVER_ERROR, "500", defaultMessage(exception, "There was an unexpected error, try again later."));
    }

    private Response buildError(Response.Status status, String code, String message) {
        ErrorsResponse body = new ErrorsResponse(List.of(new ErrorDetail(code, message)));
        return Response.status(status)
                .type(MediaType.APPLICATION_JSON)
                .entity(body)
                .build();
    }

    private String defaultMessage(Exception exception, String fallback) {
        return exception.getMessage() == null || exception.getMessage().isBlank()
                ? fallback
                : exception.getMessage();
    }
}
