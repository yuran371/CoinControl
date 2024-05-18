package com.mergeteam.coincontrol.entity;

import com.mergeteam.coincontrol.entity.enums.ExpenseCategory;
import com.mergeteam.coincontrol.entity.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue(TransactionType.Expense.name)
public class ExpenseTransaction extends Transaction {

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private ExpenseCategory category;

    @Builder(access = AccessLevel.PRIVATE)
    public ExpenseTransaction(UUID id, UUID walletId, BigDecimal amount, OffsetDateTime date, ExpenseCategory category) {
        super(id, walletId, amount, date);
        this.category = category;
    }
}
