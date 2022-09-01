package com.innowise.sharing.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotBlank(message = "The first name cannot be empty")
    private String firstName;

    @NotBlank(message = "The last name cannot be empty")
    private String lastName;

    @Email(message = "Must be a well-formed email address")
    private String email;

    private DocumentDto licenceId;
}
