package com.mergeteam.coincontrol.repository;

import com.mergeteam.coincontrol.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
}
