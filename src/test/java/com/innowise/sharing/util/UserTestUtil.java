package com.innowise.sharing.util;

import com.innowise.sharing.dto.DocumentDto;
import com.innowise.sharing.dto.UserDto;
import com.innowise.sharing.entity.Document;
import com.innowise.sharing.entity.User;

public class UserTestUtil {

    public static final Long ID = 1L;
    public static final String FIRST_NAME = "John";
    public static final String LAST_NAME = "Doe";
    public static final String EMAIL = "john_doe@yopmail.com";
    public static final Document document = DocumentTestUtil.createDocument();
    public static final DocumentDto documentDto = DocumentTestUtil.createDocumentDto();
    public static final String PASSWORD = "qwerty123";

    public static UserDto createUserDto() {
        return UserDto.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .email(EMAIL)
                .licenceId(documentDto)
                .build();
    }

    public static User createUser() {
        return new User()
                .setId(ID)
                .setFirstName(FIRST_NAME)
                .setLastName(LAST_NAME)
                .setEmail(EMAIL)
                .setLicence(document)
                .setPassword(PASSWORD);
    }
}
