package com.mergeteam.coincontrol.entity;

import com.mergeteam.coincontrol.entity.enums.ExpenseCategory;
import com.mergeteam.coincontrol.entity.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
@DiscriminatorValue(TransactionType.Expense.name)
public class ExpenseTransaction extends Transaction {

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private ExpenseCategory category;
}
