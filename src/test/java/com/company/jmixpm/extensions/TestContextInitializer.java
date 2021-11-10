package com.company.jmixpm.extensions;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class TestContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        TestPropertyValues.of(
                "main.datasource.url=jdbc:tc:postgresql:9.6.12:///postgres-test-db",
                "main.datasource.username=postgres",
                "main.datasource.password=postgres",
                "jmix.data.dbmsType=postgres",
                "jmix.liquibase.contexts=!production-data"
        ).applyTo(applicationContext.getEnvironment());
    }
}