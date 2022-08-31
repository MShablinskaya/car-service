package com.innowise.sharing.service.impl;

import com.innowise.sharing.dto.DocumentDto;
import com.innowise.sharing.dto.UserDto;
import com.innowise.sharing.entity.User;
import com.innowise.sharing.mapper.UserMapper;
import com.innowise.sharing.repository.UserRepository;
import com.innowise.sharing.service.DocumentService;
import com.innowise.sharing.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final DocumentService documentService;
    private final UserMapper mapper;

    @Override
    public User getUSerById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public UserDto getUserDtoByEmail(String email) {
        User user = userRepository.findUserByEmail(email).orElseThrow(EntityNotFoundException::new);
        String licenseNumber = user.getLicence().getLicenseNumber();
        DocumentDto documentDto = documentService.getBySerialNumber(licenseNumber);
        UserDto userDto = mapper.userToUserDto(user);
        userDto.setLicenceId(documentDto);

        return userDto;
    }

    @Override
    public User getUserByEmail(String email){
        return userRepository.findUserByEmail(email)
                .orElseThrow(EntityNotFoundException::new);
    }
}
