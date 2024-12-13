/*
 * Copyright gauravnewton
 * 13-12-2024 at 0:7 IST
 */

package com.tech.reloded.controllers;

import com.tech.reloded.documents.EksConfigDocument;
import com.tech.reloded.documents.MetaDataDocument;
import com.tech.reloded.dtos.EksConfigDTO;
import com.tech.reloded.model.EksConfigModel;
import com.tech.reloded.model.GlobalErrorResponse;
import com.tech.reloded.model.GlobalResponse;
import com.tech.reloded.services.EksConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    @PostMapping(value = "/uploadEksConfig", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = UPLOAD_EKS_CONFIG_SUMMARY, description = UPLOAD_EKS_CONFIG_DESCRIPTION, tags = {EKS_CONFIG_OPERATIONS})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Uploaded and parsed successfully", content = { @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = EksConfigModel.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid payload provided", content = { @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = GlobalErrorResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = { @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = GlobalErrorResponse.class)) }) })
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
    @Operation(summary = ADD_EKS_TO_PROFILE_SUMMARY, description = ADD_EKS_TO_PROFILE_DESCRIPTION, tags = {EKS_CONFIG_OPERATIONS})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Eks Config profile created successfully", content = { @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = EksConfigDocument.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid payload provided", content = { @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = GlobalErrorResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = { @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = GlobalErrorResponse.class)) }) })
    public ResponseEntity<?> addEksConfigToProfile(@RequestBody EksConfigDTO eksConfigDTO) {
        EksConfigDocument savedDocument = eksConfigService.addEksConfigToProfile(eksConfigDTO);
        return new ResponseEntity<>(new GlobalResponse(savedDocument, EKS_CONFIG_ADDED, HttpStatus.CREATED), HttpStatus.CREATED);
    }

    /**
     * Gets all eks config profile.
     *
     * @return the all eks config profile
     */
    @GetMapping("/getAllEksConfigProfile")
    @Operation(summary = GET_ALL_EKS_CONFIG_PROFILE_SUMMARY, description = GET_ALL_EKS_CONFIG_PROFILE_DESCRIPTION, tags = {EKS_CONFIG_OPERATIONS})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Eks profile fetched successfully", content = { @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = EksConfigDocument.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid payload provided", content = { @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = GlobalErrorResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = { @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = GlobalErrorResponse.class)) }) })
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
    @Operation(summary = UPDATE_EKS_CONFIG_PROFILE_SUMMARY, description = UPDATE_EKS_CONFIG_PROFILE_DESCRIPTION, tags = {EKS_CONFIG_OPERATIONS})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Eks profile updated successfully", content = { @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = EksConfigDocument.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid payload provided", content = { @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = GlobalErrorResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = { @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = GlobalErrorResponse.class)) }) })
    public ResponseEntity<?> updateEksConfig(@Parameter(description = "id of existing Eks Config profile", example = "675c560e2966f26282a8913d") @PathVariable("id") String id,
         @Parameter(description = "User profile name to update", example = "profile-1")
         @RequestParam("userName") String userName) {
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
    @Operation(summary = DELETE_EKS_CONFIG_SUMMARY, description = DELETE_EKS_CONFIG_DESCRIPTION, tags = {EKS_CONFIG_OPERATIONS})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Eks profile deleted successfully", content = { @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = GlobalResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid payload provided", content = { @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = GlobalErrorResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = { @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = GlobalErrorResponse.class)) }) })
    public ResponseEntity<?> deleteEksConfig(@Parameter(description = "id of existing Eks Config profile", example = "675c560e2966f26282a8913d") @PathVariable("id") String id) {
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
    @Operation(summary = GET_ALL_EKS_META_DATA_SUMMARY, description = GET_ALL_EKS_META_DATA_DESCRIPTION, tags = {EKS_CONFIG_OPERATIONS})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get all metadata list successfully", content = { @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = MetaDataDocument.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid payload provided", content = { @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = GlobalErrorResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = { @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = GlobalErrorResponse.class)) }) })
    public ResponseEntity<?> getAllMetaDataForEksConfigId(@Parameter(description = "id of existing Eks Config profile", example = "675c560e2966f26282a8913d") @PathVariable("id") String id) {
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
    @Operation(summary = UPDATE_META_DATA_SUMMARY, description = UPDATE_META_DATA_DESCRIPTION, tags = {EKS_CONFIG_OPERATIONS})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Metadata updated successfully", content = { @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = GlobalResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid payload provided", content = { @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = GlobalErrorResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = { @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = GlobalErrorResponse.class)) }) })
    public ResponseEntity<?> updateMetaData(@Parameter(description = "id of existing metadata", example = "675c560e2966f26282a8913a")  @PathVariable("id") String id,
        @Parameter(description = "Existing key name to update", example = "apiVersion")
        @RequestParam("key") String key,
        @Parameter(description = "Value of key to update", example = "v1")
        @RequestParam("value") String value) {
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
    @Operation(summary = DELETE_META_DATA_SUMMARY, description = DELETE_META_DATA_DESCRIPTION, tags = {EKS_CONFIG_OPERATIONS})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Metadata deleted successfully", content = { @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = GlobalResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid payload provided", content = { @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = GlobalErrorResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = { @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = GlobalErrorResponse.class)) }) })
    public ResponseEntity<?> deleteMetaData(@Parameter(description = "id of existing metadata", example = "675c560e2966f26282a8913a") @PathVariable("id") String id) {
        eksConfigService.deleteMetaData(id);
        return new ResponseEntity<>(new GlobalResponse(null, EKS_META_DATA_DELETED, HttpStatus.OK), HttpStatus.OK);
    }
}
