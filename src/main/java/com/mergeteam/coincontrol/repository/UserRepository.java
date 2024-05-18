package com.mergeteam.coincontrol.repository;

import com.mergeteam.coincontrol.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
