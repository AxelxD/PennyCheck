package com.compartamos.pennycheck.api;

import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.Test;

@QuarkusTest
class PennyCheckResourceTest {

    @Test
    void shouldAcceptValidationRequest() {
        String body = """
                {
                  \"accountNumber\": \"123456789012345678\",
                  \"bankCode\": \"072\",
                  \"accountType\": \"CLABE\",
                  \"customerId\": \"CLI-10001\",
                  \"requestDate\": \"2026-04-07T12:00:00\"
                }
                """;

        given()
                .contentType("application/json")
                .header("X-Correlation-Id", "corr-001")
                .body(body)
        .when()
                .post("/api/v1/pennycheck/validations")
        .then()
                .statusCode(202)
                .body("status", equalTo("RECEIVED"))
                .body("correlationId", equalTo("corr-001"));
    }
}
