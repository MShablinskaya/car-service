package com.innowise.sharing.service;

import com.innowise.sharing.dto.DocumentDto;
import com.innowise.sharing.entity.Document;
import com.innowise.sharing.exception.DocumentEntityNotFoundException;
import com.innowise.sharing.mapper.DocumentMapper;
import com.innowise.sharing.repository.DocumentRepository;
import com.innowise.sharing.service.impl.DocumentServiceImpl;
import com.innowise.sharing.util.DocumentTestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class DocumentServiceTest {
    @Mock
    private DocumentRepository documentRepository;

    @Mock
    private DocumentMapper documentMapper;

    @InjectMocks
    private DocumentServiceImpl documentService;

    @Test
    void getBySerialNumber_If_Positive() {
        DocumentDto expectedDto = DocumentTestUtil.createDocumentDto();
        Document expectedDocument = DocumentTestUtil.createDocument();

        when(documentRepository.findDocumentByLicenseNumber(DocumentTestUtil.NUMBER))
                .thenReturn(Optional.ofNullable(expectedDocument));
        assert expectedDocument != null;
        when(documentMapper.documentToDocumentDto(expectedDocument)).thenReturn(expectedDto);

        DocumentDto currentDto = documentService.getBySerialNumber(DocumentTestUtil.NUMBER);

        assertThat(currentDto).isEqualTo(expectedDto);
    }

    @Test
    void getBySerialNumber_EntityNotFoundException() {
        when(documentRepository.findDocumentByLicenseNumber(DocumentTestUtil.NUMBER))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> documentService.getBySerialNumber(DocumentTestUtil.NUMBER))
                .isInstanceOf(DocumentEntityNotFoundException.class)
                .hasMessage(String.format("Document with registration number %s not Found", DocumentTestUtil.NUMBER));
    }
}