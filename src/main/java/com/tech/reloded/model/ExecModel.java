/*
 * Copyright gauravnewton
 * 13-12-2024 at 1:1 IST
 */

package com.tech.reloded.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * The type Exec model.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExecModel {
    @Schema(example = "client.authentication.k8s.io/v1beta1")
    private String apiVersion;
    private List<String> args;
    @Schema(example = "aws")
    private String command;
}
