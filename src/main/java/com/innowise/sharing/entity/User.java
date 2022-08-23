package com.innowise.sharing.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.innowise.sharing.enums.Role;
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
import javax.validation.constraints.Pattern;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Accessors(chain = true)
public class Users {
    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "role_id")
    @Enumerated(EnumType.STRING)
    private Role roleId;

    @Column(name = "email")
    @Pattern(regexp = ".+@.+\\.[a-z]", message = "Invalid Email address!")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String email;

    @Column(name = "password")
    @Pattern(regexp = "^(&=.*\\d).{6,20}$", flags = Pattern.Flag.UNICODE_CASE)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return Objects.equals(id, users.id)
                && Objects.equals(firstName, users.firstName)
                && Objects.equals(lastName, users.lastName)
                && roleId == users.roleId
                && Objects.equals(email, users.email)
                && Objects.equals(password, users.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, roleId, email, password);
    }
}
