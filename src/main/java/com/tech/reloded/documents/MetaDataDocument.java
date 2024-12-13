/*
 * Copyright gauravnewton
 * 13-12-2024 at 14:23 IST
 */

package com.tech.reloded.documents;

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
    private String id;
    private String key;
    private String value;
}
