package com.innowise.sharing.service;

import com.innowise.sharing.dto.DocumentDto;
import com.innowise.sharing.dto.UserDto;
import com.innowise.sharing.entity.User;
import com.innowise.sharing.exception.UserEntityNotFoundException;
import com.innowise.sharing.mapper.UserMapper;
import com.innowise.sharing.repository.UserRepository;
import com.innowise.sharing.service.impl.UserServiceImpl;
import com.innowise.sharing.service.util.DocumentTestUtil;
import com.innowise.sharing.service.util.UserTestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper mapper;

    @Mock
    private DocumentService documentService;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void getUserDtoByEmailIfPositive() {
        UserDto expectedUserDto = UserTestUtil.createUserDto();
        User expectedUser = UserTestUtil.createUser();
        DocumentDto documentDto = DocumentTestUtil.createDocumentDto();

        when(userRepository.findUserByEmail(UserTestUtil.EMAIL)).thenReturn(Optional.ofNullable(expectedUser));
        when(mapper.userToUserDto(expectedUser)).thenReturn(expectedUserDto);
        when(documentService.getBySerialNumber(DocumentTestUtil.NUMBER)).thenReturn(documentDto);

        UserDto currentDto = userService.getUserDtoByEmail(UserTestUtil.EMAIL);

        assertThat(currentDto).isEqualTo(expectedUserDto);
    }

    @Test
    void getUserDtoByEmail_EntityNotFound() {

        when(userRepository.findUserByEmail(UserTestUtil.EMAIL)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.getUserDtoByEmail(UserTestUtil.EMAIL))
                .isInstanceOf(EntityNotFoundException.class);

    }

    @Test
    void getUserEntityByEmail_IfPositive() {
        User expectedUser = UserTestUtil.createUser();

        when(userRepository.findUserByEmail(UserTestUtil.EMAIL)).thenReturn(Optional.ofNullable(expectedUser));

        User user = userService.getUserByEmail(UserTestUtil.EMAIL);

        assertThat(user).isEqualTo(expectedUser);

    }

    @Test
    void getUserEntityByEmail_EntityNotFoundException() {
        when(userRepository.findUserByEmail(UserTestUtil.EMAIL)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.getUserByEmail(UserTestUtil.EMAIL))
                .isInstanceOf(UserEntityNotFoundException.class)
                .hasMessage(String.format("User entity with email:%s not found!", UserTestUtil.EMAIL));
    }
}