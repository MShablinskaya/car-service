package com.innowise.sharing.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.innowise.sharing.enums.Role;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Data
@RequiredArgsConstructor
@Accessors(chain = true)
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String email;

    @Column(name = "password")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @JoinColumn(name = "license_id")
    @OneToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Document licence;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;
}
