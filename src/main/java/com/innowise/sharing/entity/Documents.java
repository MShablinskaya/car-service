package com.innowise.sharing.entity;

import com.innowise.sharing.enums.DocumentType;
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
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Accessors(chain = true)
public class Documents {

    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "license_number")
    private Long licenseNumber;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private DocumentType type;

    @Column(name = "expiration_date")
    private Timestamp expirationDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Documents documents = (Documents) o;
        return Objects.equals(id, documents.id)
                && Objects.equals(licenseNumber, documents.licenseNumber)
                && type == documents.type
                && Objects.equals(expirationDate, documents.expirationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, licenseNumber, type, expirationDate);
    }
}
