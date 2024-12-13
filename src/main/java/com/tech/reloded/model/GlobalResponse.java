/*
 * Copyright gauravnewton
 * 13-12-2024 at 10:53 IST
 */

package com.tech.reloded.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

/**
 * The type Global response.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GlobalResponse {
    private Object data;
    @Schema(example="message")
    private String message;
    @Schema(example="200")
    private HttpStatus status;
}
