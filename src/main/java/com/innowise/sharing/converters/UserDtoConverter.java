package com.innowise.sharing.converters;

import com.innowise.sharing.dto.UserDto;
import com.innowise.sharing.entity.User;

public interface UserDtoConverter {
    UserDto toDto(User user);

    User toEntity(UserDto dto);
}
