/*
 * Copyright gauravnewton
 * 13-12-2024 at 1:7 IST
 */

package com.tech.reloded.services;

import com.tech.reloded.documents.EksConfigDocument;
import com.tech.reloded.documents.MetaDataDocument;
import com.tech.reloded.dtos.EksConfigDTO;
import com.tech.reloded.model.EksConfigModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


/**
 * The interface Eks config service.
 */
public interface EksConfigService {
    /**
     * Upload eks config file and parse it eks config model.
     *
     * @param file the file
     * @return the eks config model
     */
    public EksConfigModel uploadEksConfigFileAndParseItAsEksConfigModel(MultipartFile file);

    /**
     * Upload eks config file and parse it as map map.
     *
     * @param file the file
     * @return the map
     */
    public Map<String, Object> uploadEksConfigFileAndParseItAsMap(MultipartFile file);

    /**
     * Add eks config to profile eks config document.
     *
     * @param eksConfigDTO the eks config dto
     * @return the eks config document
     */
    public EksConfigDocument addEksConfigToProfile(EksConfigDTO eksConfigDTO);

    /**
     * Gets all eks config profile.
     *
     * @return the all eks config profile
     */
    public List<EksConfigDocument> getAllEksConfigProfile();

    /**
     * Update eks config.
     *
     * @param id       the id
     * @param userName the user name
     */
    public void updateEksConfig(String id, String userName);

    /**
     * Delete eks config.
     *
     * @param id the id
     */
    public void deleteEksConfig(String id);

    /**
     * Gets all meta data for eks config id.
     *
     * @param id the id
     * @return the all meta data for eks config id
     */
    public List<MetaDataDocument> getAllMetaDataForEksConfigId(String id);

    /**
     * Update meta data.
     *
     * @param id    the id
     * @param key   the key
     * @param value the value
     */
    public void updateMetaData(String id, String key, String value);

    /**
     * Delete meta data.
     *
     * @param id the id
     */
    public void deleteMetaData(String id);
}
