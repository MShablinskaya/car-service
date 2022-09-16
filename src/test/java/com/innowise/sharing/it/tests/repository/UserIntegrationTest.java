package com.innowise.sharing.it.tests.repository;

import com.innowise.sharing.entity.User;
import com.innowise.sharing.enums.Role;
import com.innowise.sharing.it.BaseIntegrationTest;
import com.innowise.sharing.repository.UserRepository;
import com.innowise.sharing.util.UserTestUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Sql({"/data/erasure_documents.sql", "/data/insert_documents.sql", "/data/erasure_users.sql", "/data/insert_users.sql"})
class UserIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    private final User expectedUser = new User()
            .setId(1L)
            .setFirstName("John")
            .setLastName("Doe")
            .setEmail("john_doe@yopmail.com")
            .setPassword("qwerty123")
            .setRole(Role.OWNER);

    @Test
    void findUserByEmail_When_Success() {
        User currentUser = userRepository.findUserByEmail(expectedUser.getEmail()).orElseThrow();

        assertNotNull(currentUser);
        assertThat(currentUser.getEmail()).isEqualTo(expectedUser.getEmail());
        assertThat(currentUser.getPassword()).isEqualTo(expectedUser.getPassword());
    }


    @Test
    void save_When_Success() {
        User currentUser = userRepository.save(expectedUser);

        assertNotNull(currentUser);
        assertThat(currentUser.getEmail()).isEqualTo(expectedUser.getEmail());
        assertThat(currentUser.getPassword()).isEqualTo(expectedUser.getPassword());
    }
}
