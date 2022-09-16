package com.innowise.sharing.util;

import com.innowise.sharing.dto.DocumentDto;
import com.innowise.sharing.entity.Document;
import com.innowise.sharing.enums.DocumentType;

import java.time.Instant;

public class DocumentTestUtil {
    public static final Long ID = 1L;
    public static final String NUMBER = "GIBDD 7725";
    public static final DocumentType TYPE = DocumentType.DRIVER_LICENSE;
    public static final Instant EXPIRATION_DATE = Instant.parse("2022-09-12T16:05:25.356401995Z");

    public static DocumentDto createDocumentDto() {
        return DocumentDto.builder()
                .licenseNumber(NUMBER)
                .expirationDate(EXPIRATION_DATE)
                .build();
    }

    public static Document createDocument() {
        return new Document()
                .setId(ID)
                .setLicenseNumber(NUMBER)
                .setType(TYPE)
                .setExpirationDate(EXPIRATION_DATE);
    }

}
