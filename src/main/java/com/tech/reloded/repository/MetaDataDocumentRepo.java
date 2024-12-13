/*
 * Copyright gauravnewton
 * 13-12-2024 at 14:32 IST
 */

package com.tech.reloded.repository;

import com.tech.reloded.documents.MetaDataDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Meta data document repo.
 */
@Repository
public interface MetaDataDocumentRepo extends MongoRepository<MetaDataDocument, String> {
}
