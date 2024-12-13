/*
 * Copyright gauravnewton
 * 13-12-2024 at 8:55 IST
 */

package com.tech.reloded.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * The type Global error response.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GlobalErrorResponse {
    @Schema(example = "500")
    private HttpStatus status;
    @Schema(example = "Internal server error")
    private String message;
    private List<String> errorCodes;
}
