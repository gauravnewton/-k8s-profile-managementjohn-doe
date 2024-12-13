/*
 * Copyright gauravnewton
 * 13-12-2024 at 0:52 IST
 */

package com.tech.reloded.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    private String apiVersion;
    private List<ClusterModel> clusters;
    private List<ContextModel> contexts;
    @JsonProperty("current-context")
    private String currentContext;
    private String kind;
    private Object preferences;
    private List<UserModel> users;
}
