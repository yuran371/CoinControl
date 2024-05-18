package com.mergeteam.coincontrol.api;

import com.mergeteam.coincontrol.api.sort.TransactionSort;
import com.mergeteam.coincontrol.entity.ExpenseTransaction;
import com.mergeteam.coincontrol.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class TransactionRestController {

    private final TransactionService transactionService;
    @GetMapping("transactions/expense")
    public ResponseEntity<Page<ExpenseTransaction>> getAllExpenseTransactions(UUID userId,  @RequestParam("offset") Integer offset,
                                                                              @RequestParam("limit") Integer limit,
                                                                              @RequestParam(value = "sort", defaultValue = "DATE_DESC") TransactionSort sort) {
//        return transactionService.findAll(
//                PageRequest.of(offset, limit, sort.getSortValue());
        return null;
    }
}
