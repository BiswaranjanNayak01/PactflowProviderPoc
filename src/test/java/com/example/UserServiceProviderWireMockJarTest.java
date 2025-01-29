package com.example;

import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactBroker;
import au.com.dius.pact.provider.junitsupport.loader.PactBrokerAuth;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.github.tomakehurst.wiremock.client.WireMock.*;


@Provider("UserService")
@PactBroker(url = "https://andolasoft.pactflow.io", authentication = @PactBrokerAuth(token = "0n2qLV7daRXgyNZgWQ0n9w"))
public class UserServiceProviderWireMockJarTest {

    @BeforeEach
    void setUp(PactVerificationContext context) {
        // Start your provider service on port 8080 (or update the port)
        context.setTarget(new HttpTestTarget("localhost", 8080));
    }

    // Add this method to handle the provider state "User 1 exists"
    @State("User 1 exists")
    public void setupUser1() {
        // Setup code to ensure "User 1 exists" in your provider (e.g., insert into a test database)
        System.out.println("Setting up state: User 1 exists");
    }

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    void verifyPact(PactVerificationContext context) {
        context.verifyInteraction();
    }
}
