/*
 * Copyright gauravnewton
 * 13-12-2024 at 0:53 IST
 */

package com.tech.reloded.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * The type Cluster model.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClusterModel {
    @JsonProperty("certificate-authority-data")
    private String certificateAuthorityData;
    private String server;
    private String name;
}
