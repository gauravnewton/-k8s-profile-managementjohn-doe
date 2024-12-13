/*
 * Copyright gauravnewton
 * 13-12-2024 at 0:54 IST
 */

package com.tech.reloded.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * The type Context model.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ContextModel {
    @Schema(example = "arn:aws:eks:eu-west-3:335425316266:cluster/abcd-eks-cluster")
    private String cluster;
    @Schema(example = "arn:aws:eks:eu-west-3:335425316266:cluster/abcd-eks-cluster")
    private String user;
    @Schema(example = "arn:aws:eks:eu-west-3:335425316266:cluster/abcd-eks-cluster")
    private String name;
}
