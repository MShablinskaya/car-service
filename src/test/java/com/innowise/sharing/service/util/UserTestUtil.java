package com.innowise.sharing.service.util;

import com.innowise.sharing.dto.DocumentDto;
import com.innowise.sharing.dto.UserDto;
import com.innowise.sharing.entity.Document;
import com.innowise.sharing.entity.User;

public class UserTestUtil {

    public static final Long ID = 1L;
    public static final String FIRST_NAME = "first";
    public static final String LAST_NAME = "last";
    public static final String EMAIL = "email@example.com";
    public static final Document document = DocumentTestUtil.createDocument();
    public static final DocumentDto documentDto = DocumentTestUtil.createDocumentDto();

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
                .setLicence(document);
    }
}
