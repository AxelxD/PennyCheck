package com.compartamos.pennycheck.api;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@QuarkusTest
class PennyCheckResourceTest {

    @Test
    void shouldAcceptValidationRequest() {
        String body = """
                {
                  "accountValidator": {
                    "externalId": "391d151f-1cac-44e7-a05b-79a1199621d6",
                    "subscriptionId": "7c8a0230-36e0-43f4-9b7a-581dc55ea9c3",
                    "bankId": 40012,
                    "accountType": "1",
                    "identifierType": "CLABE",
                    "accountIdentifier": "123456789012345678",
                    "name": "Juan",
                    "secondName": "Pruebas",
                    "lastName": "Ocho",
                    "secondLastName": "Seis",
                    "citizenId": "SSSS010101SSSSXXXX",
                    "taxPayerIdentificationNumber": "SSSS010101XXX",
                    "verifierCompany": "Empresa1"
                  }
                }
                """;

        given()
                .contentType("application/json")
                .header("x-api-key", "sandbox-key")
                .body(body)
        .when()
                .post("/sandbox/v1/bavs/accountValidator")
        .then()
                .statusCode(200)
                .body("operation", equalTo("request"))
                .body("message", equalTo("The bank account verification request has been received."))
                .body("externalId", equalTo("391d151f-1cac-44e7-a05b-79a1199621d6"))
                .body("subscriptionId", equalTo("7c8a0230-36e0-43f4-9b7a-581dc55ea9c3"))
                .body("inquiryId", notNullValue());
    }

    @Test
    void shouldReturnValidationResult() {
        given()
                .contentType("application/json")
                .header("x-api-key", "sandbox-key")
        .when()
                .get("/sandbox/v1/bavs/accountValidator/a19fb6b8-2677-44f2-9cd7-3b2f78bb6f8c")
        .then()
                .statusCode(200)
                .body("operation", equalTo("consumption"))
                .body("result.reasonProcess.code", equalTo("200.10"))
                .body("result.request.identifierType", equalTo("CLABE"));
    }

    @Test
    void shouldRejectMissingApiKey() {
        given()
                .contentType("application/json")
                .body("""
                        {
                          "accountValidator": {
                            "externalId": "391d151f-1cac-44e7-a05b-79a1199621d6",
                            "subscriptionId": "7c8a0230-36e0-43f4-9b7a-581dc55ea9c3",
                            "bankId": 40012,
                            "accountType": "1",
                            "identifierType": "CLABE",
                            "accountIdentifier": "123456789012345678",
                            "name": "Juan",
                            "verifierCompany": "Empresa1"
                          }
                        }
                        """)
        .when()
                .post("/sandbox/v1/bavs/accountValidator")
        .then()
                .statusCode(400)
                .body("errors[0].code", equalTo("400"))
                .body("errors[0].message", equalTo("Missign parameters {x-api-key}."));
    }
}
