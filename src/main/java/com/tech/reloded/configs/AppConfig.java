/*
 * Copyright gauravnewton
 * 12-12-2024 at 20:12 IST
 */

package com.tech.reloded.configs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * The type App config.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Configuration
public class AppConfig {

    @Value("${developer.name}")
    private String developerName;

    @Value("${developer.email}")
    private String developerEmail;

    @Value("${developer.webUrl}")
    private String developerWebUrl;

    @Value("${application.upload-dir}")
    private String uploadDir;
}
