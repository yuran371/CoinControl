package com.mergeteam.coincontrol.repository;

import com.mergeteam.coincontrol.entity.User;
import com.mergeteam.coincontrol.utils.IT;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/*
    Unit test
 */
@RequiredArgsConstructor
@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest extends IntegrationBaseClass{

    private final UserRepository userRepository;

    private User user;
    @BeforeEach
    void setUpUser() {
        user = new User();
        user.setId(UUID.randomUUID());
        user.setEmail("test@example.com");
        user.setName("Test User");
        user.setPassword("password");
        user.setAvatarPath("avatar/path");
    }

    @Test
    public void testFindByEmail() throws DataAccessException {
        // Given
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));

        // When
        Optional<User> foundUser = userRepository.findByEmail("test@example.com");

        // Then
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getEmail()).isEqualTo("test@example.com");
    }

    @Test
    public void testFindByEmailNotFound() throws DataAccessException {
        // Given
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        // When
        Optional<User> foundUser = userRepository.findByEmail("nonexistent@example.com");

        // Then
        assertThat(foundUser).isNotPresent();
    }

//    @Test
//    void checkPageable() {
//        Pageable pageable = PageRequest.of(1,2, Sort.by("id"));
//        Slice<User> slice = userRepository.findAllBy(pageable);
//        slice.forEach((user -> System.out.println("ttt" + user.getId())));
//
//        while (slice.hasNext()) {
//            slice = userRepository.findAllBy(slice.nextPageable());
//            slice.forEach((user -> System.out.println("rrr"+user.getId())));
//        }
//    }

//    @ParameterizedTest
}
