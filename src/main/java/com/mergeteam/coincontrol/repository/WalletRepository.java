package com.mergeteam.coincontrol.repository;

import com.mergeteam.coincontrol.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WalletRepository extends JpaRepository<Wallet, UUID> {
    Optional<Wallet> getWalletByUserIdAndId(UUID userId, UUID id);

    List<Wallet> findAllByUserId(UUID userId);
}
