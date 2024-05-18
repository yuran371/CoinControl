package com.mergeteam.coincontrol.repository;

import com.mergeteam.coincontrol.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WalletRepository extends JpaRepository<Wallet, UUID> {
}
