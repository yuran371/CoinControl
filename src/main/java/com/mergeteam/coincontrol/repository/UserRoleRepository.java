package com.mergeteam.coincontrol.repository;

import com.mergeteam.coincontrol.entity.User;
import com.mergeteam.coincontrol.security.tokenAuth.entities.Role;
import com.mergeteam.coincontrol.security.tokenAuth.entities.UserRole;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UUID> {

    @Transactional(readOnly = true)
    @Query("select u from UserRole u where u.user.email=:email")
    Optional<UserRole> findUserRoleByEmail(String email) throws DataAccessException;

    @Transactional(readOnly = true)
    List<UserRole> findUserRolesByRole(Role role);
}
