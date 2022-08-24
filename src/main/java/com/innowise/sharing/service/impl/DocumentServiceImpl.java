package com.innowise.sharing.service.impl;

import com.innowise.sharing.converters.DocumentDtoConverter;
import com.innowise.sharing.dto.DocumentDto;
import com.innowise.sharing.repository.DocumentRepository;
import com.innowise.sharing.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {
    private final DocumentRepository documentRepository;
    private final DocumentDtoConverter converter;

    @Override
    public DocumentDto getBySerialNumber(String serialNumber) {
        return converter.toDto(documentRepository.findDocumentByLicenseNumber(serialNumber));
    }
}
