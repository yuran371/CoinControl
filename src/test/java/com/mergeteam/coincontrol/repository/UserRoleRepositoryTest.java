package com.mergeteam.coincontrol.repository;

import com.mergeteam.coincontrol.integration.IntegrationBaseClass;
import com.mergeteam.coincontrol.security.tokenAuth.entities.Role;
import com.mergeteam.coincontrol.security.tokenAuth.entities.UserRole;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
public class UserRoleRepositoryTest extends IntegrationBaseClass {

    private final UserRoleRepository userRoleRepository;

    @Test
    public void testUserRoleEntityByEmail() {
        Optional<UserRole> userRoleByEmail = userRoleRepository.findUserRoleByEmail("123@mail.ru");
        assertTrue(userRoleByEmail.isPresent());
        assertTrue(userRoleByEmail.get().getRole().describeConstable()
                .isPresent());
        assertEquals(Role.ROLE_USER, userRoleByEmail.get().getRole());
        System.out.println(userRoleByEmail);
    }
    @Test
    public void testUserRoleEntityById() {
        UUID id = UUID.fromString("2378202d-4c06-4688-916c-0e09ae845d09");
        Optional<UserRole> byId = userRoleRepository.findById(id);
        UserRole userRole = byId.get();
        System.out.println("123123123"+userRole);
    }

    @Test
    public void testUserRoleGetRole() {
        List<UserRole> roles = userRoleRepository.findUserRolesByRole(Role.ROLE_USER);
        System.out.println("11111"+roles);

    }
    @Test
    public void testUserRoleSave() {
//        UserRole userRole = new UserRole(user, Role.ROLE_USER);

//        System.out.println("123123123"+userRole);
    }

}
