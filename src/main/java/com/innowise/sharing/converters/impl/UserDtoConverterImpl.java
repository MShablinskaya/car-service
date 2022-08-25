package com.innowise.sharing.converters.impl;

import com.innowise.sharing.converters.DocumentDtoConverter;
import com.innowise.sharing.converters.UserDtoConverter;
import com.innowise.sharing.dto.UserDto;
import com.innowise.sharing.entity.User;
import com.innowise.sharing.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDtoConverterImpl implements UserDtoConverter {
    private final DocumentService documentService;
    private final DocumentDtoConverter converter;

    @Override
    public UserDto toDto(User user) {
        return new UserDto()
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setEmail(user.getEmail())
                .setLicenceId(documentService.getBySerialNumber(user.getLicence().getLicenseNumber()));
    }

    @Override
    public User toEntity(UserDto dto) {
        return new User()
                .setFirstName(dto.getFirstName())
                .setLastName(dto.getLastName())
                .setEmail(dto.getEmail())
                .setLicence(converter.toEntity(documentService.getBySerialNumber(dto.getLicenceId().getLicenseNumber())));
    }
}
