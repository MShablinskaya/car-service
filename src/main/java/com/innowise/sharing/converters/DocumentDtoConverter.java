package com.innowise.sharing.converters;

import com.innowise.sharing.dto.DocumentDto;
import com.innowise.sharing.entity.Document;

public interface DocumentDtoConverter {
    DocumentDto toDto(Document document);

    Document toEntity(DocumentDto dto);
}
