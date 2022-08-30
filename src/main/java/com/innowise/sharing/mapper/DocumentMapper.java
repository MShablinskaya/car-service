package com.innowise.sharing.mapper;

import com.innowise.sharing.dto.DocumentDto;
import com.innowise.sharing.entity.Document;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DocumentMapper {
    DocumentDto documentToDocumentDto(Document document);
    Document documentDtoToDocument(DocumentDto dto);
}
