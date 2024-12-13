/*
 * Copyright gauravnewton
 * 13-12-2024 at 0:52 IST
 */

package com.tech.reloded.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * The type Eks config model.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EksConfigModel {
    @Schema(example = "v1")
    private String apiVersion;
    private List<ClusterModel> clusters;
    private List<ContextModel> contexts;
    @JsonProperty("current-context")
    @Schema(example = "arn:aws:eks:eu-west-3:335425316266:cluster/abcd-eks-cluster")
    private String currentContext;
    @Schema(example = "Config")
    private String kind;
    private Object preferences;
    private List<UserModel> users;
}
