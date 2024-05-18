package com.mergeteam.coincontrol.repository;

import com.mergeteam.coincontrol.entity.ExpenseTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ExpenseTransactionRepository extends JpaRepository<ExpenseTransaction, UUID> {

    @Query("select e from ExpenseTransaction e join fetch e.walletId where e.walletId.id in :walletIds")
    Page<ExpenseTransaction> findAllByWalletsId(List<UUID> walletIds, Pageable pageable);
}
