package com.innowise.sharing.dto;

import com.innowise.sharing.service.util.DocumentTestUtil;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DocumentDtoTest {
    public static final Instant EXPIRATION_DATE = Instant.parse("2022-09-12T16:05:25.356401995Z");

    @Test
    void testDocumentDtoToString() {

        DocumentDto dto = DocumentTestUtil.createDocumentDto();
        assertThat(dto.toString()).hasToString("DocumentDto(licenseNumber=GIBDD 7725, expirationDate=" + EXPIRATION_DATE + ")");
    }

}