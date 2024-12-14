/*
 * Copyright gauravnewton
 * 13-12-2024 at 1:14 IST
 */

package com.tech.reloded.utils;

import com.tech.reloded.configs.AppConfig;
import com.tech.reloded.exceptions.types.FileNotFoundException;
import com.tech.reloded.exceptions.types.FileUploadException;
import com.tech.reloded.exceptions.types.InvalidFileException;
import com.tech.reloded.exceptions.types.WritePermissionException;
import com.tech.reloded.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.tech.reloded.constants.ApplicationConstant.CONTENT_TYPE_APPLICATION_YML;

/**
 * The type File utils.
 */
@Component
public class FileUtils {
    @Autowired
    private AppConfig appConfig;

    /**
     * Upload file string.
     *
     * @param file the file
     * @return the string
     */
    public String uploadFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new FileNotFoundException("No file provided to upload");
        }
        File uploadDir = new File(appConfig.getUploadDir());
        if (!uploadDir.exists()) {
            if (uploadDir.mkdirs()) {
                throw new WritePermissionException("Disk write access not available");
            }
        }
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        if (fileName.contains("..") || !Objects.requireNonNull(file.getContentType()).equalsIgnoreCase(CONTENT_TYPE_APPLICATION_YML)) {
            throw new InvalidFileException("Invalid file selected to upload");
        }
        try {
            Path path = Path.of(appConfig.getUploadDir(), fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new FileUploadException("File upload error");
        }
        return uploadDir + "/" + file.getOriginalFilename();
    }

    /**
     * Parse yaml eks config model.
     *
     * @param filePath the file path
     * @return the eks config model
     */
    public EksConfigModel parseYaml(String filePath) {
        try (InputStream inputStream = new FileInputStream(filePath)) {
            Yaml yaml = new Yaml();
            Map<String, Object> yamlMap = yaml.load(inputStream);
            return parseYamlMapToEksConfig(yamlMap);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            throw new InvalidFileException("yaml file seems to be invalid, please refer sample file structure");
        }
    }

    /**
     * Parse yaml map to eks config eks config model.
     *
     * @param yamlMap the yaml map
     * @return the eks config model
     */
    public EksConfigModel parseYamlMapToEksConfig(Map<String, Object> yamlMap) {
        EksConfigModel eksConfigModel = new EksConfigModel();
        eksConfigModel.setApiVersion((String) yamlMap.get("apiVersion"));
        populateClusters(eksConfigModel, yamlMap);
        populateContexts(eksConfigModel, yamlMap);
        populateUsers(eksConfigModel, yamlMap);
        eksConfigModel.setCurrentContext((String) yamlMap.get("current-context"));
        eksConfigModel.setKind((String) yamlMap.get("kind"));
        eksConfigModel.setPreferences((Object) yamlMap.get("preferences"));

        return eksConfigModel;
    }

    private void populateUsers(EksConfigModel eksConfigModel, Map<String, Object> yamlMap) {
        List<UserModel> userModels = new ArrayList<>();
        List<Map<String, Object>> userList = (List<Map<String, Object>>) yamlMap.get("users");
        for (Map<String, Object> context : userList) {
            UserModel userModelObject = new UserModel();
            userModelObject.setName((String) context.get("name"));
            Map<String, Object> innerUser = (Map<String, Object>) context.get("user");
            Map<String, Object> execMap = (Map<String, Object>) innerUser.get("exec");
            ExecModel execModel = new ExecModel();
            execModel.setApiVersion((String) execMap.get("apiVersion"));
            execModel.setArgs((List<String>) execMap.get("args"));
            execModel.setCommand((String) execMap.get("command"));
            userModelObject.setExec(execModel);
            userModels.add(userModelObject);
        }
        eksConfigModel.setUsers(userModels);
    }

    private void populateContexts(EksConfigModel eksConfigModel, Map<String, Object> yamlMap) {
        List<ContextModel> contextModels = new ArrayList<>();
        List<Map<String, Object>> contextList = (List<Map<String, Object>>) yamlMap.get("contexts");
        for (Map<String, Object> context : contextList) {
            ContextModel contextModelObject = new ContextModel();
            contextModelObject.setName((String) context.get("name"));
            Map<String, Object> innerContext = (Map<String, Object>) context.get("context");
            contextModelObject.setCluster((String) innerContext.get("cluster"));
            contextModelObject.setUser((String) innerContext.get("user"));
            contextModels.add(contextModelObject);
        }
        eksConfigModel.setContexts(contextModels);
    }

    private void populateClusters(EksConfigModel eksConfigModel, Map<String, Object> yamlMap) {
        List<ClusterModel> clusterModels = new ArrayList<>();
        List<Map<String, Object>> clusterList = (List<Map<String, Object>>) yamlMap.get("clusters");
        for (Map<String, Object> cluster : clusterList) {
            ClusterModel clusterModelObject = new ClusterModel();
            clusterModelObject.setName((String) cluster.get("name"));
            Map<String, Object> innerCluster = (Map<String, Object>) cluster.get("cluster");
            clusterModelObject.setCertificateAuthorityData((String) innerCluster.get("certificate-authority-data"));
            clusterModelObject.setServer((String) innerCluster.get("server"));
            clusterModels.add(clusterModelObject);
        }
        eksConfigModel.setClusters(clusterModels);
    }
}
