package com.compartamos.pennycheck.exception;

public class UnauthorizedWebhookException extends RuntimeException {
    public UnauthorizedWebhookException(String message) {
        super(message);
    }
}
