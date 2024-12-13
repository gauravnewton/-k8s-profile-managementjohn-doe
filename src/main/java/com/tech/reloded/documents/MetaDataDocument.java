/*
 * Copyright gauravnewton
 * 13-12-2024 at 14:23 IST
 */

package com.tech.reloded.documents;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * The type Meta data document.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "metaData")
public class MetaDataDocument {
    @Id
    @Schema(example = "675c560e2966f26282a8913a")
    private String id;
    @Schema(example = "apiVersion")
    private String key;
    @Schema(example = "client.authentication.k8s.io/v1beta1")
    private String value;
}
