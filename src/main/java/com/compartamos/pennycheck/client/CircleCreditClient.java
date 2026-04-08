package com.compartamos.pennycheck.client;

import com.compartamos.pennycheck.model.CircleCreditRequest;
import com.compartamos.pennycheck.model.CircleCreditResponse;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/bank-account-verification")
@RegisterRestClient(configKey = "circle-credit-api")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface CircleCreditClient {

    @POST
    CircleCreditResponse verifyAccount(CircleCreditRequest request);
}
