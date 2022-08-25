package com.innowise.sharing.entity;

import com.innowise.sharing.enums.DocumentType;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Data
@RequiredArgsConstructor
@Accessors(chain = true)
@Table(name = "documents")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "license_number")
    private String licenseNumber;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private DocumentType type;

    @Column(name = "expiration_date")
    private Timestamp expirationDate;
}
