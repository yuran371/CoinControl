package com.mergeteam.coincontrol.repository;

import com.mergeteam.coincontrol.entity.ExpenseTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExpenseTransactionRepository extends JpaRepository<ExpenseTransaction, UUID> {

    Page<ExpenseTransaction> findByWalletId(UUID walletId, Pageable pageable);
}
