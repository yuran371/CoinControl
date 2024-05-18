package com.mergeteam.coincontrol.repository;

import com.mergeteam.coincontrol.api.sort.TransactionSort;
import com.mergeteam.coincontrol.entity.ExpenseTransaction;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class ExpenseTransactionRepositoryIT extends IntegrationBaseClass {

    private final ExpenseTransactionRepository expenseTransactionRepository;

    @Test
    void findAllByWalletsId() {
        Pageable unpaged = Pageable.unpaged();
        List<UUID> list = Stream.of("74dd2764-c0cc-4195-a906-b8edd7804c03", "55646589-a2c7-456e-bed4-7c8b3d45e22c")
                .map(UUID::fromString).toList();
        PageRequest pageRequest = PageRequest.of(0, 10, TransactionSort.AMOUNT_ASC.getSortValue());
        Page<ExpenseTransaction> allByWalletsId = expenseTransactionRepository.findAllByWalletsId(list, pageRequest);
    }
}
