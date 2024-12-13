/*
 * Copyright gauravnewton
 * 12-12-2024 at 20:26 IST
 */

package com.tech.reloded.documents;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * The type Eks config document.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "eksConfig")
public class EksConfigDocument {
    @Id
    @Schema(example = "675c560e2966f26282a8913d")
    private String id;
    @DBRef
    private List<MetaDataDocument> metaData;
    @Schema(example = "profile-1")
    private String userName;
}
