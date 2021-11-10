package com.company.jmixpm.extensions;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.PostgreSQLContainer;

public class PostgreSqlExtension implements BeforeAllCallback, AfterAllCallback {

    private PostgreSQLContainer container;

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        container = new PostgreSQLContainer("postgres:alpine")
                .withDatabaseName("postgres-test-db")
                .withUsername("postgres")
                .withPassword("postgres");
        container.start();
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        container.stop();
    }

}