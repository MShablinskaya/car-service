package com.innowise.sharing.it.tests.repository;

import com.innowise.sharing.entity.Document;
import com.innowise.sharing.enums.DocumentType;
import com.innowise.sharing.it.BaseIntegrationTest;
import com.innowise.sharing.repository.DocumentRepository;
import com.innowise.sharing.util.DocumentTestUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.time.Instant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@Sql({"/data/erasure_documents.sql", "/data/insert_documents.sql"})
class DocumentRepositoryIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private DocumentRepository documentRepository;

    private final Document expectedDocument = new Document()
            .setId(1L)
            .setLicenseNumber("GIBDD 7725")
            .setType(DocumentType.DRIVER_LICENSE)
            .setExpirationDate(Instant.parse("2022-09-12T16:05:25.356401995Z"));

    @Test
    void findDocumentBySerialNumber_When_Success() {
        Document currentDocument = documentRepository.findDocumentByLicenseNumber(expectedDocument.getLicenseNumber())
                .orElseThrow();

        assertNotNull(currentDocument);
        assertThat(currentDocument.getLicenseNumber()).isEqualTo(expectedDocument.getLicenseNumber());
        assertThat(currentDocument.getId()).isEqualTo(expectedDocument.getId());
    }

    @Test
    void findDocumentBySerialNumber_When_Entity_Not_Found() {
        Document currentDocument = documentRepository.findDocumentByLicenseNumber("NUMBER").orElse(null);

        assertNull(currentDocument);
    }

}
