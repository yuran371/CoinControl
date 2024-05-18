package com.mergeteam.coincontrol.repository;

import com.mergeteam.coincontrol.entity.ExpenseTransaction;
import com.mergeteam.coincontrol.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface TransactionRepository<T extends Transaction> extends JpaRepository<T, UUID> {

    @Query("select p from #{#entityName} p where p.userId = :id")
    Page<T> findAllByUserId(int id);

}
