/*
 * Copyright gauravnewton
 * 12-12-2024 at 20:13 IST
 */

package com.tech.reloded.constants;

/**
 * The type Application constant.
 */
public class ApplicationConstant {

    /**
     * The constant API.
     */
    public static final String API = "api";

    /**
     * The constant VERSION.
     */
    public static final String VERSION = "v1";

    /**
     * The constant APP_TITLE.
     */
    public static final String APP_TITLE = "kubernetes-profile-management";

    /**
     * The constant APP_DESCRIPTION.
     */
    public static final String APP_DESCRIPTION = "kubernetes profile management demo application";

    /**
     * The constant CONTENT_TYPE_APPLICATION_YML.
     */
    public static final String CONTENT_TYPE_APPLICATION_YML = "application/x-yaml";

    /**
     * The constant YAML_UPLOADED_AND_PARSED_SUCCESSFULLY.
     */
    public static final String YAML_UPLOADED_AND_PARSED_SUCCESSFULLY = "Yaml uploaded abd parsed successfully";

    /**
     * The constant EKS_CONFIG_ADDED.
     */
    public static final String EKS_CONFIG_ADDED = "Eks Config added to profile";

    /**
     * The constant ALL_EKS_CONFIG_FETCHED.
     */
    public static final String ALL_EKS_CONFIG_FETCHED = "All Eks Config profile loaded successfully";
    /**
     * The constant EKS_CONFIG_UPDATED.
     */
    public static final String EKS_CONFIG_UPDATED ="Eks Config patched successfully";

    /**
     * The constant EKS_CONFIG_DELETED.
     */
    public static final String EKS_CONFIG_DELETED = "Eks Config deleted successfully";

    /**
     * The constant META_DATA_FETCHED.
     */
    public static final String META_DATA_FETCHED = "All Metadata fetched successfully";

    /**
     * The constant EKS_META_DATA_UPDATED.
     */
    public static final String EKS_META_DATA_UPDATED = "Metadata patched successfully";
    /**
     * The constant EKS_META_DATA_DELETED.
     */
    public static final String EKS_META_DATA_DELETED = "Metadata deleted successfully";

    /**
     * The constant APPLICATION_JSON.
     */
    public static final String APPLICATION_JSON = "application/json";

    /**
     * The constant UPLOAD_EKS_CONFIG_SUMMARY.
     */
    public static final String UPLOAD_EKS_CONFIG_SUMMARY = "Upload and parse yaml config file";

    /**
     * The constant EKS_CONFIG_OPERATIONS.
     */
    public static final String EKS_CONFIG_OPERATIONS = "Eks Config Operations";

    /**
     * The constant UPLOAD_EKS_CONFIG_DESCRIPTION.
     */
    public static final String UPLOAD_EKS_CONFIG_DESCRIPTION = "This end point will upload a valid Eks yaml config file and then parse it into json format";
    /**
     * The constant ADD_EKS_TO_PROFILE_SUMMARY.
     */
    public static final String ADD_EKS_TO_PROFILE_SUMMARY = "Add Eks Config to profile";
    /**
     * The constant ADD_EKS_TO_PROFILE_DESCRIPTION.
     */
    public static final String ADD_EKS_TO_PROFILE_DESCRIPTION = "This end point will attached selected Eks config to user profile";
    /**
     * The constant GET_ALL_EKS_CONFIG_PROFILE_SUMMARY.
     */
    public static final String GET_ALL_EKS_CONFIG_PROFILE_SUMMARY = "Get all existing Eks Config list";
    /**
     * The constant GET_ALL_EKS_CONFIG_PROFILE_DESCRIPTION.
     */
    public static final String GET_ALL_EKS_CONFIG_PROFILE_DESCRIPTION = "This end point will return all available Eks Config profile from system";
    /**
     * The constant UPDATE_EKS_CONFIG_PROFILE_SUMMARY.
     */
    public static final String UPDATE_EKS_CONFIG_PROFILE_SUMMARY = "Update existing Eks Config profile";
    /**
     * The constant UPDATE_EKS_CONFIG_PROFILE_DESCRIPTION.
     */
    public static final String UPDATE_EKS_CONFIG_PROFILE_DESCRIPTION = "This end point will update an existing Eks Config profile by providing config id";
    public static final String DELETE_EKS_CONFIG_SUMMARY = "Delete existing Eks Config";
    public static final String DELETE_EKS_CONFIG_DESCRIPTION = "This end point will delete existing Eks Config and related metadata by providing a valid Eks Config Id";
    public static final String GET_ALL_EKS_META_DATA_SUMMARY = "Get all existing Eks metadata";
    public static final String GET_ALL_EKS_META_DATA_DESCRIPTION = "This end point will fetch all metadata related to Eks Config profile by providing a valid Eks Config profile id";
    public static final String UPDATE_META_DATA_SUMMARY = "Update existing metadata";
    public static final String UPDATE_META_DATA_DESCRIPTION = "This end point will update existing metadata by providing a valid metadata id";
    public static final String DELETE_META_DATA_SUMMARY = "Delete existing metadata";
    public static final String DELETE_META_DATA_DESCRIPTION = "This end point will delete an existing metadata by providing a valid metadata id";
}
