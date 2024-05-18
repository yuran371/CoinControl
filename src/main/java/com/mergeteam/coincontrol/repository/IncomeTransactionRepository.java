package com.mergeteam.coincontrol.repository;

import com.mergeteam.coincontrol.entity.ExpenseTransaction;
import com.mergeteam.coincontrol.entity.IncomeTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface IncomeTransactionRepository extends JpaRepository<IncomeTransaction, UUID> {
    @Query("select i from IncomeTransaction i join fetch i.walletId where i.walletId.id in :walletIds")
    Page<IncomeTransaction> findAllByWalletsId(List<UUID> walletIds, Pageable pageable);
}
