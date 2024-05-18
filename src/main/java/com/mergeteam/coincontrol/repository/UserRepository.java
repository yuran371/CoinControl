package com.mergeteam.coincontrol.repository;

import com.mergeteam.coincontrol.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Slice<User> findAllBy(Pageable pageable);

}
