/*
 * Copyright gauravnewton
 * 13-12-2024 at 15:53 IST
 */

package com.tech.reloded.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import io.swagger.v3.oas.annotations.media.Schema;


/**
 * The type Meta data dto.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MetaDataDTO {
    @Schema(example="apiVersion")
    private String key;
    @Schema(example="v1")
    private String value;
}
