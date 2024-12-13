/*
 * Copyright gauravnewton
 * 13-12-2024 at 1:4 IST
 */

package com.tech.reloded.model;

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
    private String message;
    private HttpStatus statusCode;
}
