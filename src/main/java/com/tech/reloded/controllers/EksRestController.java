/*
 * Copyright gauravnewton
 * 13-12-2024 at 0:7 IST
 */

package com.tech.reloded.controllers;

import com.tech.reloded.documents.EksConfigDocument;
import com.tech.reloded.documents.MetaDataDocument;
import com.tech.reloded.dtos.EksConfigDTO;
import com.tech.reloded.model.EksConfigModel;
import com.tech.reloded.model.GlobalResponse;
import com.tech.reloded.services.EksConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.tech.reloded.constants.ApplicationConstant.*;

/**
 * The type Eks rest controller.
 */
@RestController
@RequestMapping("/eks")
public class EksRestController {

    @Autowired
    private EksConfigService eksConfigService;

    /**
     * Upload eks config yaml file response entity.
     *
     * @param file the file
     * @return the response entity
     */
    @PostMapping("/uploadEksConfig")
    public ResponseEntity<?> uploadEksConfigYamlFile(@RequestParam("file") MultipartFile file) {
        EksConfigModel eksConfigModel = eksConfigService.uploadEksConfigFileAndParseIt(file);
        return new ResponseEntity<>(new GlobalResponse(eksConfigModel, YAML_UPLOADED_AND_PARSED_SUCCESSFULLY, HttpStatus.OK), HttpStatus.OK);
    }

    /**
     * Add eks config to profile response entity.
     *
     * @param eksConfigDTO the eks config dto
     * @return the response entity
     */
    @PostMapping("/addEksConfigToProfile")
    public ResponseEntity<?> addEksConfigToProfile(@RequestBody EksConfigDTO eksConfigDTO) {
        EksConfigDocument savedDocument = eksConfigService.addEksConfigToProfile(eksConfigDTO);
        return new ResponseEntity<>(new GlobalResponse(savedDocument, EKS_CONFIG_ADDED, HttpStatus.OK), HttpStatus.OK);
    }

    /**
     * Gets all eks config profile.
     *
     * @return the all eks config profile
     */
    @GetMapping("/getAllEksConfigProfile")
    public ResponseEntity<?> getAllEksConfigProfile() {
        List<EksConfigDocument> savedDocument = eksConfigService.getAllEksConfigProfile();
        return new ResponseEntity<>(new GlobalResponse(savedDocument, ALL_EKS_CONFIG_FETCHED, HttpStatus.OK), HttpStatus.OK);
    }

    /**
     * Update eks config response entity.
     *
     * @param id       the id
     * @param userName the user name
     * @return the response entity
     */
    @PatchMapping("/updateEksConfig/{id}")
    public ResponseEntity<?> updateEksConfig(@PathVariable("id") String id, @RequestParam("userName") String userName) {
        eksConfigService.updateEksConfig(id, userName);
        return new ResponseEntity<>(new GlobalResponse(null, EKS_CONFIG_UPDATED, HttpStatus.OK), HttpStatus.OK);
    }

    /**
     * Delete eks config response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/deleteEksConfig/{id}")
    public ResponseEntity<?> deleteEksConfig(@PathVariable("id") String id) {
        eksConfigService.deleteEksConfig(id);
        return new ResponseEntity<>(new GlobalResponse(null, EKS_CONFIG_DELETED, HttpStatus.OK), HttpStatus.OK);
    }

    /**
     * Gets all meta data for eks config id.
     *
     * @param id the id
     * @return the all meta data for eks config id
     */
    @GetMapping("/getAllMetaDataForEksConfigId/{id}")
    public ResponseEntity<?> getAllMetaDataForEksConfigId(@PathVariable("id") String id) {
        List<MetaDataDocument> metaDataList = eksConfigService.getAllMetaDataForEksConfigId(id);
        return new ResponseEntity<>(new GlobalResponse(metaDataList, META_DATA_FETCHED, HttpStatus.OK), HttpStatus.OK);
    }

    /**
     * Update meta data response entity.
     *
     * @param id    the id
     * @param key   the key
     * @param value the value
     * @return the response entity
     */
    @PatchMapping("/updateMetaData/{id}")
    public ResponseEntity<?> updateMetaData(@PathVariable("id") String id, @RequestParam("key") String key, @RequestParam("value") String value) {
        eksConfigService.updateMetaData(id, key, value);
        return new ResponseEntity<>(new GlobalResponse(null, EKS_META_DATA_UPDATED, HttpStatus.OK), HttpStatus.OK);
    }

    /**
     * Delete meta data response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/deleteMetaData/{id}")
    public ResponseEntity<?> deleteMetaData(@PathVariable("id") String id) {
        eksConfigService.deleteMetaData(id);
        return new ResponseEntity<>(new GlobalResponse(null, EKS_META_DATA_DELETED, HttpStatus.OK), HttpStatus.OK);
    }
}
