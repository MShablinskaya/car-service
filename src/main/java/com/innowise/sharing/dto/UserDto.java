package com.innowise.sharing.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Builder
public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    private DocumentDto licenceId;
}
