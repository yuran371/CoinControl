package com.mergeteam.coincontrol.integration.repository;

import com.mergeteam.coincontrol.entity.User;
import com.mergeteam.coincontrol.integration.IT;
import com.mergeteam.coincontrol.integration.IntegrationBaseClass;
import com.mergeteam.coincontrol.repository.UserRepository;
import com.mergeteam.coincontrol.security.tokenAuth.entities.Role;
import com.mergeteam.coincontrol.security.tokenAuth.entities.UserRole;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.annotation.Rollback;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

//import static org.junit.Assert.*;

/*
    Unit test
 */
@IT
@RequiredArgsConstructor
@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest extends IntegrationBaseClass {

    @Autowired
    private UserRepository userRepository;

    private User user1;
    private User user2;
    private UserRole role1;
    private UserRole role2;

    @BeforeEach
    void setUp() {
        user1 = User.builder()
                .id(UUID.randomUUID())
                .email("user1@example.com")
                .name("User One")
                .password("password1")
                .avatarPath("/ava")
                .roles(new HashSet<>())
                .wallets(new ArrayList<>())
                .build();

        user2 = User.builder()
                .id(UUID.randomUUID())
                .email("user2@example.com")
                .name("User Two")
                .password("password2")
                .avatarPath("/ava")
                .roles(new HashSet<>())
                .wallets(new ArrayList<>())
                .build();

        role1 = new UserRole(UUID.randomUUID(), user1, Role.ROLE_BANNED);
        role2 = new UserRole(UUID.randomUUID(), user2, Role.ROLE_BANNED);

        user1.addUserRole(role1);
        user2.addUserRole(role2);

        userRepository.save(user1);
        userRepository.save(user2);
    }

    @Test
    @Rollback
    void testFindUsersByRoles() {
        List<User> users = userRepository.findDistinctByRolesRole(Role.ROLE_BANNED);
        assertEquals(2, users.size());
        assertTrue(users.stream().anyMatch(user -> user.getEmail().equals("user1@example.com")));
        assertTrue(users.stream().anyMatch(u -> u.getRoles().stream().anyMatch(r -> r.getRole().equals(Role.ROLE_BANNED))));
        assertFalse(users.stream().anyMatch(user -> user.getRoles().equals(Role.ROLE_USER)));
    }

    @Test
    public void testFindByEmail() throws DataAccessException {
        Optional<User> user = userRepository.findByEmail("user1@example.com");
        String expected = "user1@example.com";
        String actual = user.get().getEmail();
        String wrong = "user2@example.com";
        Assertions.assertTrue(user.isPresent());
        assertEquals(expected, actual);
        assertNotEquals(wrong, actual);
    }

    @Test
    public void testUserRolesEntityById() {
        UUID id = UUID.fromString("e0ee31f6-6c9b-4987-8fba-246b059b3bee");
        Optional<User> byId = userRepository.findById(id);
        System.out.println(byId);
    }

}
