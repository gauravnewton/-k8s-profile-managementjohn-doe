/*
 * Copyright gauravnewton
 * 13-12-2024 at 1:7 IST
 */

package com.tech.reloded.services.impl;

import com.tech.reloded.configs.AppConfig;
import com.tech.reloded.documents.EksConfigDocument;
import com.tech.reloded.documents.MetaDataDocument;
import com.tech.reloded.dtos.EksConfigDTO;
import com.tech.reloded.dtos.MetaDataDTO;
import com.tech.reloded.exceptions.types.InvalidEksConfigIdException;
import com.tech.reloded.model.EksConfigModel;
import com.tech.reloded.repository.EksConfigDocumentRepo;
import com.tech.reloded.repository.MetaDataDocumentRepo;
import com.tech.reloded.services.EksConfigService;
import com.tech.reloded.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The type Eks config service.
 */
@Service
public class EksConfigServiceImpl implements EksConfigService {

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private EksConfigDocumentRepo eksConfigRepo;

    @Autowired
    private MetaDataDocumentRepo metaDataDocumentRepo;

    @Autowired
    private FileUtils fileUtils;

    @Override
    public EksConfigModel uploadEksConfigFileAndParseItAsEksConfigModel(MultipartFile file) {
        String filePath = fileUtils.uploadFile(file);
        return fileUtils.parseYamlAsEksConfigModel(filePath);
    }

    @Override
    public Map<String, Object> uploadEksConfigFileAndParseItAsMap(MultipartFile file) {
        String filePath = fileUtils.uploadFile(file);
        return fileUtils.parseYamlAsMap(filePath);
    }

    @Override
    public EksConfigDocument addEksConfigToProfile(EksConfigDTO eksConfigDTO) {
        EksConfigDocument eksConfigDocument = new EksConfigDocument();
        List<MetaDataDocument> metaDataDocumentList = new ArrayList<>();
        for (MetaDataDTO metaDataDTO : eksConfigDTO.getMetaData()) {
            MetaDataDocument metaDataDocument = new MetaDataDocument();
            metaDataDocument.setKey(metaDataDTO.getKey());
            metaDataDocument.setValue(metaDataDTO.getValue());
            metaDataDocumentList.add(metaDataDocument);
        }
        metaDataDocumentRepo.saveAll(metaDataDocumentList);
        eksConfigDocument.setUserName(eksConfigDTO.getUserName());
        eksConfigDocument.setMetaData(metaDataDocumentList);
        return eksConfigRepo.save(eksConfigDocument);
    }

    @Override
    public List<EksConfigDocument> getAllEksConfigProfile() {
        return eksConfigRepo.findAll();
    }

    @Override
    public void updateEksConfig(String id, String userName) {
        Optional<EksConfigDocument> eksConfigDocument = eksConfigRepo.findById(id);
        if (eksConfigDocument.isEmpty()) {
            throw new InvalidEksConfigIdException("Invalid eks config id provided");
        }
        eksConfigDocument.get().setUserName(userName);
        eksConfigRepo.save(eksConfigDocument.get());
    }

    @Override
    public void deleteEksConfig(String id) {
        Optional<EksConfigDocument> eksConfigDocument = eksConfigRepo.findById(id);
        if (eksConfigDocument.isEmpty()) {
            throw new InvalidEksConfigIdException("Invalid eks config id provided");
        }
        metaDataDocumentRepo.deleteAll(eksConfigDocument.get().getMetaData());
        eksConfigRepo.delete(eksConfigDocument.get());
    }

    @Override
    public List<MetaDataDocument> getAllMetaDataForEksConfigId(String id) {
        Optional<EksConfigDocument> eksConfigDocument = eksConfigRepo.findById(id);
        if (eksConfigDocument.isEmpty()) {
            throw new InvalidEksConfigIdException("Invalid eks config id provided");
        }
        return eksConfigDocument.get().getMetaData();
    }

    @Override
    public void updateMetaData(String id, String key, String value) {
        Optional<MetaDataDocument> metaDataDocument = metaDataDocumentRepo.findById(id);
        if (metaDataDocument.isEmpty()) {
            throw new InvalidEksConfigIdException("Invalid Metadata id provided");
        }
        metaDataDocument.get().setValue(value);
        metaDataDocument.get().setKey(key);
        metaDataDocumentRepo.save(metaDataDocument.get());
    }

    @Override
    public void deleteMetaData(String id) {
        Optional<MetaDataDocument> metaDataDocument = metaDataDocumentRepo.findById(id);
        if (metaDataDocument.isEmpty()) {
            throw new InvalidEksConfigIdException("Invalid meta data id provided");
        }
        metaDataDocumentRepo.delete(metaDataDocument.get());
    }
}
