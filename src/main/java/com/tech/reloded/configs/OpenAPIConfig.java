/*
 * Copyright gauravnewton
 * 12-12-2024 at 20:8 IST
 */

package com.tech.reloded.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.tech.reloded.constants.ApplicationConstant.*;


/**
 * The type Open api config.
 */
@Configuration
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpenAPIConfig {
    @Autowired
    private AppConfig appConfig;

    /**
     * Registration open api open api.
     *
     * @return the open api
     */
    @Bean
    public OpenAPI registrationOpenAPI() {
        return new OpenAPI()
                .info(new Info().title(APP_TITLE)
                        .contact(getContact())
                        .description(APP_DESCRIPTION)
                        .version(VERSION));
    }

    private Contact getContact() {
        Contact contact = new Contact();
        contact.setName(appConfig.getDeveloperName());
        contact.setUrl(appConfig.getDeveloperWebUrl());
        contact.setEmail(appConfig.getDeveloperEmail());
        return contact;
    }
}
