package com.tech.reloded.services.impl;

import com.tech.reloded.configs.AppConfig;
import com.tech.reloded.documents.EksConfigDocument;
import com.tech.reloded.documents.MetaDataDocument;
import com.tech.reloded.dtos.EksConfigDTO;
import com.tech.reloded.dtos.MetaDataDTO;
import com.tech.reloded.exceptions.types.InvalidEksConfigIdException;
import com.tech.reloded.model.*;
import com.tech.reloded.repository.EksConfigDocumentRepo;
import com.tech.reloded.repository.MetaDataDocumentRepo;
import com.tech.reloded.utils.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * The type Eks config service impl test.
 */
@ExtendWith(MockitoExtension.class)
public class EksConfigServiceImplTest {
    @Mock
    private AppConfig mockAppConfig;
    @Mock
    private EksConfigDocumentRepo mockEksConfigRepo;
    @Mock
    private MetaDataDocumentRepo mockMetaDataDocumentRepo;
    @Mock
    private FileUtils mockFileUtils;
    @InjectMocks
    private EksConfigServiceImpl eksConfigServiceImplUnderTest;
    private static final String mockEksConfigDocumentId = "675c67083e19de03804e6ace";
    private static final String mockMetaDataDocumentId = "675c56ae5742e94ebe6fe6d9";
    private static final String mockUserName = "profile-1";
    private static final String mockKey = "apiVersion";
    private static final String mockValue = "v1";

    /**
     * Test upload eks config file and parse it.
     *
     * @throws Exception the exception
     */
    @Test
    public void testUploadEksConfigFileAndParseIt() throws Exception {
        // Setup
        final MultipartFile file = new MockMultipartFile("mockFileName", "mockFileContent".getBytes());
        final EksConfigModel expectedResult = new EksConfigModel();
        expectedResult.setApiVersion("apiVersion");

        final ClusterModel clusterModel = new ClusterModel();
        clusterModel.setCertificateAuthorityData("certificateAuthorityData");
        clusterModel.setServer("server");
        clusterModel.setName("name");

        expectedResult.setClusters(List.of(clusterModel));

        final ContextModel contextModel = new ContextModel();
        contextModel.setCluster("cluster");
        contextModel.setUser("user");
        contextModel.setName("name");

        expectedResult.setContexts(List.of(contextModel));
        expectedResult.setCurrentContext("currentContext");
        expectedResult.setKind("kind");
        expectedResult.setPreferences("preferences");

        final UserModel userModel = new UserModel();
        userModel.setName("name");

        final ExecModel exec = new ExecModel();
        exec.setApiVersion("apiVersion");
        exec.setArgs(List.of("value"));
        exec.setCommand("command");

        userModel.setExec(exec);

        expectedResult.setUsers(List.of(userModel));

        when(mockFileUtils.uploadFile(any(MultipartFile.class))).thenReturn("mockedUploadedFilPath");
        when(mockFileUtils.parseYaml("mockedUploadedFilPath")).thenReturn(expectedResult);

        final EksConfigModel result = eksConfigServiceImplUnderTest.uploadEksConfigFileAndParseIt(file);

        assertThat(result).isEqualTo(expectedResult);
        verify(mockFileUtils).uploadFile(any(MultipartFile.class));
        verify(mockFileUtils).parseYaml("mockedUploadedFilPath");
    }

    /**
     * Test add eks config to profile.
     */
    @Test
    public void testAddEksConfigToProfile() {
        final EksConfigDTO eksConfigDTO = new EksConfigDTO(List.of(new MetaDataDTO(mockKey, mockValue)), mockUserName);

        final MetaDataDocument metaDataEntity = new MetaDataDocument(mockMetaDataDocumentId, mockKey, mockValue);
        final EksConfigDocument eksConfigDocumentEntity = new EksConfigDocument(mockEksConfigDocumentId, List.of(metaDataEntity), mockUserName);

        MetaDataDocument metaDataDocument = new MetaDataDocument(null, mockKey, mockValue);
        EksConfigDocument eksConfigDocument = new EksConfigDocument(null, List.of(metaDataDocument), mockUserName);

        when(mockEksConfigRepo.save(eksConfigDocument)).thenReturn(eksConfigDocumentEntity);

        final EksConfigDocument result = eksConfigServiceImplUnderTest.addEksConfigToProfile(eksConfigDTO);

        assertNotNull(result.getId());
        assertNotNull(result.getMetaData().get(0).getId());
        verify(mockMetaDataDocumentRepo).saveAll(List.of(metaDataDocument));
        verify(mockEksConfigRepo).save(eksConfigDocument);
    }

    /**
     * Test get all eks config profile.
     */
    @Test
    public void testGetAllEksConfigProfile() {
        final MetaDataDocument metaDataDocument = new MetaDataDocument(mockMetaDataDocumentId, mockKey, mockValue);
        final EksConfigDocument eksConfigDocument = new EksConfigDocument(mockEksConfigDocumentId, List.of(metaDataDocument), mockUserName);

        final List<EksConfigDocument> expectedResult = List.of(eksConfigDocument);

        final MetaDataDocument metaDataDocumentEntity = new MetaDataDocument(mockMetaDataDocumentId, mockKey, mockValue);
        final EksConfigDocument elsConfigDocumentEntity = new EksConfigDocument(mockEksConfigDocumentId, List.of(metaDataDocumentEntity), mockUserName);
        final List<EksConfigDocument> eksConfigDocuments = List.of(elsConfigDocumentEntity);

        when(mockEksConfigRepo.findAll()).thenReturn(eksConfigDocuments);

        final List<EksConfigDocument> result = eksConfigServiceImplUnderTest.getAllEksConfigProfile();
        assertEquals(expectedResult, result);
        verify(mockEksConfigRepo).findAll();
    }

    /**
     * Test get all eks config profile eks config document repo returns no items.
     */
    @Test
    public void testGetAllEksConfigProfile_EksConfigDocumentRepoReturnsNoItems() {
        when(mockEksConfigRepo.findAll()).thenReturn(Collections.emptyList());
        final List<EksConfigDocument> result = eksConfigServiceImplUnderTest.getAllEksConfigProfile();
        assertEquals(Collections.emptyList(), result);
    }

    /**
     * Test update eks config.
     */
    @Test
    public void testUpdateEksConfig() {
        final MetaDataDocument metaDataDocument = new MetaDataDocument(mockMetaDataDocumentId, mockKey, mockValue);
        final EksConfigDocument eksConfigDocument1 = new EksConfigDocument(mockEksConfigDocumentId, List.of(metaDataDocument), mockUserName);
        final Optional<EksConfigDocument> eksConfigDocument = Optional.of(eksConfigDocument1);
        when(mockEksConfigRepo.findById(mockEksConfigDocumentId)).thenReturn(eksConfigDocument);

        eksConfigServiceImplUnderTest.updateEksConfig(mockEksConfigDocumentId, mockUserName);

        final MetaDataDocument metaDataDocumentEntity = new MetaDataDocument(mockMetaDataDocumentId, mockKey, mockValue);
        final EksConfigDocument eksConfigDocumentEntity = new EksConfigDocument(mockEksConfigDocumentId, List.of(metaDataDocumentEntity), mockUserName);

        verify(mockEksConfigRepo).save(eksConfigDocumentEntity);
        verify(mockEksConfigRepo).findById(mockEksConfigDocumentId);
    }

    /**
     * Test update eks config eks config document repo find by id returns absent.
     */
    @Test
    public void testUpdateEksConfig_EksConfigDocumentRepoFindByIdReturnsAbsent() {
        when(mockEksConfigRepo.findById(mockEksConfigDocumentId)).thenReturn(Optional.empty());
        Assertions.assertThrows(InvalidEksConfigIdException.class, () -> {
            eksConfigServiceImplUnderTest.updateEksConfig(mockEksConfigDocumentId, mockUserName);
        });
    }

    /**
     * Test delete eks config.
     */
    @Test
    public void testDeleteEksConfig() {
        final MetaDataDocument metaDataDocument = new MetaDataDocument(mockMetaDataDocumentId, mockKey, mockValue);
        final EksConfigDocument eksConfigDocument = new EksConfigDocument(mockEksConfigDocumentId, List.of(metaDataDocument), mockUserName);

        final Optional<EksConfigDocument> eksConfigDocumentList = Optional.of(eksConfigDocument);

        when(mockEksConfigRepo.findById(mockEksConfigDocumentId)).thenReturn(eksConfigDocumentList);

        eksConfigServiceImplUnderTest.deleteEksConfig(mockEksConfigDocumentId);

        final MetaDataDocument metaDataDocumentEntity = new MetaDataDocument(mockMetaDataDocumentId, mockKey, mockValue);
        final EksConfigDocument eksConfigDocumentEntity = new EksConfigDocument(mockEksConfigDocumentId, List.of(metaDataDocumentEntity), mockUserName);

        verify(mockEksConfigRepo).findById(mockEksConfigDocumentId);
        verify(mockMetaDataDocumentRepo).deleteAll(List.of(new MetaDataDocument(mockMetaDataDocumentId, mockKey, mockValue)));
        verify(mockEksConfigRepo).delete(eksConfigDocumentEntity);
    }

    /**
     * Test delete eks config eks config document repo find by id returns absent.
     */
    @Test
    public void testDeleteEksConfig_EksConfigDocumentRepoFindByIdReturnsAbsent() {
        when(mockEksConfigRepo.findById(mockEksConfigDocumentId)).thenReturn(Optional.empty());
        Assertions.assertThrows(InvalidEksConfigIdException.class, () -> {
            eksConfigServiceImplUnderTest.deleteEksConfig(mockEksConfigDocumentId);
        });
    }

    /**
     * Test get all meta data for eks config id.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetAllMetaDataForEksConfigId() throws Exception {
        final List<MetaDataDocument> expectedResult = List.of(new MetaDataDocument(mockMetaDataDocumentId, mockKey, mockValue));

        final MetaDataDocument metaDataDocument = new MetaDataDocument(mockMetaDataDocumentId, mockKey, mockValue);
        final EksConfigDocument eksConfigDocument = new EksConfigDocument(mockEksConfigDocumentId, List.of(metaDataDocument), mockUserName);

        final Optional<EksConfigDocument> eksConfigDocumentList = Optional.of(eksConfigDocument);
        when(mockEksConfigRepo.findById(mockEksConfigDocumentId)).thenReturn(eksConfigDocumentList);

        final List<MetaDataDocument> result = eksConfigServiceImplUnderTest.getAllMetaDataForEksConfigId(mockEksConfigDocumentId);

        assertEquals(expectedResult, result);
        verify(mockEksConfigRepo).findById(mockEksConfigDocumentId);
    }

    /**
     * Test get all meta data for eks config id eks config document repo returns absent.
     */
    @Test
    public void testGetAllMetaDataForEksConfigId_EksConfigDocumentRepoReturnsAbsent() {
        when(mockEksConfigRepo.findById(mockEksConfigDocumentId)).thenReturn(Optional.empty());
        Assertions.assertThrows(InvalidEksConfigIdException.class, () -> {
            eksConfigServiceImplUnderTest.getAllMetaDataForEksConfigId(mockEksConfigDocumentId);
        });
    }

    /**
     * Test update meta data.
     */
    @Test
    public void testUpdateMetaData() {
        final Optional<MetaDataDocument> metaDataDocument = Optional.of(new MetaDataDocument(mockMetaDataDocumentId, mockKey, mockValue));
        when(mockMetaDataDocumentRepo.findById(mockMetaDataDocumentId)).thenReturn(metaDataDocument);

        eksConfigServiceImplUnderTest.updateMetaData(mockMetaDataDocumentId, mockKey, mockValue);

        verify(mockMetaDataDocumentRepo).save(new MetaDataDocument(mockMetaDataDocumentId, mockKey, mockValue));
    }

    /**
     * Test update meta data meta data document repo find by id returns absent.
     */
    @Test
    public void testUpdateMetaData_MetaDataDocumentRepoFindByIdReturnsAbsent() {
        when(mockMetaDataDocumentRepo.findById(mockMetaDataDocumentId)).thenReturn(Optional.empty());
        Assertions.assertThrows(InvalidEksConfigIdException.class, () -> {
            eksConfigServiceImplUnderTest.updateMetaData(mockMetaDataDocumentId, mockKey, mockValue);
        });
    }

    /**
     * Test delete meta data.
     */
    @Test
    public void testDeleteMetaData() {
        final Optional<MetaDataDocument> metaDataDocument = Optional.of(new MetaDataDocument(mockMetaDataDocumentId, mockKey, mockValue));
        when(mockMetaDataDocumentRepo.findById(mockMetaDataDocumentId)).thenReturn(metaDataDocument);

        eksConfigServiceImplUnderTest.deleteMetaData(mockMetaDataDocumentId);

        verify(mockMetaDataDocumentRepo).delete(new MetaDataDocument(mockMetaDataDocumentId, mockKey, mockValue));
    }

    /**
     * Test delete meta data meta data document repo find by id returns absent.
     */
    @Test
    public void testDeleteMetaData_MetaDataDocumentRepoFindByIdReturnsAbsent() {
        when(mockMetaDataDocumentRepo.findById(mockMetaDataDocumentId)).thenReturn(Optional.empty());
        Assertions.assertThrows(InvalidEksConfigIdException.class, () -> {
            eksConfigServiceImplUnderTest.deleteMetaData(mockMetaDataDocumentId);
        });
    }
}
