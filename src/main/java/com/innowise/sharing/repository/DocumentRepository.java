package com.innowise.sharing.repository;

import com.innowise.sharing.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    Optional<Document> findDocumentByLicenseNumber(String licenseNumber);
}
