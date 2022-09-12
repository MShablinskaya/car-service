package com.innowise.sharing.dto;

import com.innowise.sharing.valid.group.OnCreateGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @Pattern(regexp = "[А-Яа-яё\\p{Alpha}]{1,20}", message = "The first name must consist of letters.")
    private String firstName;

    @Pattern(regexp = "[А-Яа-яё\\p{Alpha}]{1,30}", message = "The last name must consist of letters.")
    private String lastName;

    @NotNull(groups = OnCreateGroup.class, message = "You need to specify user email.")
    @Email(message = "Must be a well-formed email address.")
    private String email;

    private DocumentDto licenceId;
}
