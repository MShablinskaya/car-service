package com.innowise.sharing.mapper;

import com.innowise.sharing.dto.DocumentDto;
import com.innowise.sharing.entity.Document;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DocumentMapper {
    DocumentMapper INSTANCE = Mappers.getMapper(DocumentMapper.class);

    DocumentDto documentToDocumentDto(Document document);
}
