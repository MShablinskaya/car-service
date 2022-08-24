package com.innowise.sharing.repository;

import com.innowise.sharing.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    Document findDocumentByLicenseNumber(String licenseNumber);
}
