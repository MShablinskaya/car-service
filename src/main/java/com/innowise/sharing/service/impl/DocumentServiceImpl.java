package com.innowise.sharing.service.impl;

import com.innowise.sharing.dto.DocumentDto;
import com.innowise.sharing.entity.Document;
import com.innowise.sharing.mapper.DocumentMapper;
import com.innowise.sharing.repository.DocumentRepository;
import com.innowise.sharing.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {
    private final DocumentRepository documentRepository;

    @Override
    public DocumentDto getBySerialNumber(String serialNumber) {
        Document document = documentRepository.findDocumentByLicenseNumber(serialNumber)
                .orElseThrow(EntityNotFoundException::new);

        return DocumentMapper.INSTANCE.documentToDocumentDto(document);
    }
}
