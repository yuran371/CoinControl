package com.mergeteam.coincontrol.repository;

import com.mergeteam.coincontrol.entity.IncomeTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IncomeTransactionRepository extends JpaRepository<IncomeTransaction, UUID> {

}
