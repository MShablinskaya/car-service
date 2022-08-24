package com.innowise.sharing.converters.impl;

import com.innowise.sharing.converters.DocumentDtoConverter;
import com.innowise.sharing.dto.DocumentDto;
import com.innowise.sharing.entity.Document;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DocumentDtoConverterImpl implements DocumentDtoConverter {
    @Override
    public DocumentDto toDto(Document document) {
        return new DocumentDto()
                .setExpirationDate(document.getExpirationDate())
                .setLicenseNumber(document.getLicenseNumber());
    }

    @Override
    public Document toEntity(DocumentDto dto) {
        return new Document()
                .setExpirationDate(dto.getExpirationDate())
                .setLicenseNumber(dto.getLicenseNumber());
    }
}
