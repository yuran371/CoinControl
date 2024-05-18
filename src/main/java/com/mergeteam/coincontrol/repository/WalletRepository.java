package com.mergeteam.coincontrol.repository;

import com.mergeteam.coincontrol.entity.User;
import com.mergeteam.coincontrol.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface WalletRepository extends JpaRepository<Wallet, UUID> {
    Optional<Wallet> getWalletByUserIdAndId(UUID userId, UUID id);

    @Query("SELECT w.user FROM Wallet w WHERE w.id = :walletId")
    User findUserByWalletId(@Param("walletId") UUID walletId);

}
