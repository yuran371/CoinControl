package com.mergeteam.coincontrol.repository;

import com.mergeteam.coincontrol.entity.User;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @Transactional(readOnly = true)
    Optional<User> findByEmail(String email) throws DataAccessException;

}
