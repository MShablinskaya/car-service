package com.innowise.sharing.mapper;

import com.innowise.sharing.dto.UserDto;
import com.innowise.sharing.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto userToUserDto(User user);
}
