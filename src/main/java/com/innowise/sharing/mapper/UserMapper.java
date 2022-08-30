package com.innowise.sharing.mapper;

import com.innowise.sharing.dto.UserDto;
import com.innowise.sharing.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto userToUserDto(User user);
    User userDtoToUser(UserDto dto);
}
