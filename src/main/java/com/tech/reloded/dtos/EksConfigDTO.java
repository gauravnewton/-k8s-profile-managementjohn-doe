/*
 * Copyright gauravnewton
 * 13-12-2024 at 15:50 IST
 */

package com.tech.reloded.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * The type Eks config dto.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EksConfigDTO {
    private List<MetaDataDTO> metaData;
    @Schema(example="profile-1")
    private String userName;
}
