package com.company.jmixpm.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.vault.core.VaultTemplate;

@Configuration
@Profile("dev")
public class VaultDbConfig {

    @Autowired
    private VaultTemplate vaultTemplate;


    @Bean("dataSourceProperties")
    DataSourceProperties dataSourcePropertiesVault() {
        DataSourceProperties properties = new DataSourceProperties();

        DbConfig creds = new ObjectMapper().convertValue(
                vaultTemplate.read("secret/data/application/creds").getData().get("data"),
                DbConfig.class);

        properties.setUrl(creds.getUrl());
        properties.setPassword(creds.getPassword());
        properties.setUsername(creds.getUsername());
        return properties;
    }


}