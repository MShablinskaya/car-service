package com.innowise.sharing.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    private DocumentDto licenceId;
}
