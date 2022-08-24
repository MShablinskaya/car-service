package com.innowise.sharing.service.impl;

import com.innowise.sharing.converters.UserDtoConverter;
import com.innowise.sharing.dto.UserDto;
import com.innowise.sharing.repository.UserRepository;
import com.innowise.sharing.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDtoConverter converter;
    private final UserRepository userRepository;

    @Override
    public UserDto getUserByEmail(String email) {
        return converter.toDto(userRepository.findUserByEmail(email));
    }
}
