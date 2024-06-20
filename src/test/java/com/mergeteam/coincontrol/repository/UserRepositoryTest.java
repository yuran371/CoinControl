package com.mergeteam.coincontrol.repository;

import com.mergeteam.coincontrol.entity.IncomeTransaction;
import com.mergeteam.coincontrol.entity.User;
import com.mergeteam.coincontrol.integration.IntegrationBaseClass;
import com.mergeteam.coincontrol.security.tokenAuth.entities.UserRole;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;

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
public class UserRepositoryTest extends IntegrationBaseClass {

    private final UserRepository userRepository;
    private final IncomeTransactionRepository incomeTransactionRepository;

    @Mock
    private User user;
//    @BeforeEach
//    void setUpUser() {
//        user = new User();
//        user.setId(UUID.randomUUID());
//        user.setEmail("test@example.com");
//        user.setName("Test User");
//        user.setPassword("password");
//        user.setAvatarPath("avatar/path");
//    }

    @Test
    public void testFindByEmail() throws DataAccessException {

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));

        Optional<User> foundUser = userRepository.findByEmail("test@example.com");

        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getEmail()).isEqualTo("test@example.com");
    }

    @Test
    public void testFindByEmailNotFound() throws DataAccessException {

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        Optional<User> foundUser = userRepository.findByEmail("nonexistent@example.com");

        assertThat(foundUser).isNotPresent();
    }

    @Test
    public void testUserRolesEntityById() {
        UUID id = UUID.fromString("e0ee31f6-6c9b-4987-8fba-246b059b3bee");
        Optional<User> byId = userRepository.findById(id);
        System.out.println(byId);
    }
    @Test
    public void testIncomeTransactionRepository() {
        UUID id = UUID.fromString("2378202d-4c06-4688-916c-0e09ae845d09");
        Optional<IncomeTransaction> byId = incomeTransactionRepository.findById(id);
        System.out.println(byId);
    }


}
