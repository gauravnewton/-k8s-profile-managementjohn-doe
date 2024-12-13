/*
 * Copyright gauravnewton
 * 13-12-2024 at 15:53 IST
 */

package com.tech.reloded.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * The type Meta data dto.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MetaDataDTO {
    private String key;
    private String value;
}
