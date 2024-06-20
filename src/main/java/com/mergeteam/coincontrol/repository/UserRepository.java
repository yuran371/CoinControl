package com.mergeteam.coincontrol.repository;

import com.mergeteam.coincontrol.entity.User;
import com.mergeteam.coincontrol.security.tokenAuth.entities.UserRole;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    @Transactional(readOnly = true)
    @Query("select u from User u left join fetch u.roles where u.email=:email")
    Optional<User> findByEmail(String email) throws DataAccessException;

    @Query("select u.role from UserRole u where u.user.email=:email")
    List<String> fetchUserRoles(@Param("email") String email);

}
