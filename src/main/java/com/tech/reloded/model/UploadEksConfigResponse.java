/*
 * Copyright gauravnewton
 * 13-12-2024 at 1:4 IST
 */

package com.tech.reloded.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

/**
 * The type Upload eks config response.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UploadEksConfigResponse {
    private EksConfigModel eksConfigModel;
    @Schema(example = "message from server")
    private String message;
    @Schema(example = "200")
    private HttpStatus statusCode;
}
