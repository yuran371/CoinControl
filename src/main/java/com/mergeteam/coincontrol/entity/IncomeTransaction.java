package com.mergeteam.coincontrol.entity;


import com.mergeteam.coincontrol.entity.enums.ExpenseCategory;

import com.mergeteam.coincontrol.entity.enums.IncomeCategory;
import com.mergeteam.coincontrol.entity.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@DiscriminatorValue(TransactionType.Income.name)
public class IncomeTransaction extends Transaction {

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private IncomeCategory category;

    @Builder(access = AccessLevel.PRIVATE)
    public IncomeTransaction(UUID id, Wallet walletId, BigDecimal amount, OffsetDateTime date, IncomeCategory category) {
        super(id, walletId, amount, date);
        this.category = category;
    }
}
