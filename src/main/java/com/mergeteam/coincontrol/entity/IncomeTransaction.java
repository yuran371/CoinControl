package com.mergeteam.coincontrol.entity;

import com.mergeteam.coincontrol.entity.enums.ExpenseCategory;
import com.mergeteam.coincontrol.entity.enums.IncomeCategory;
import com.mergeteam.coincontrol.entity.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
@DiscriminatorValue(TransactionType.Income.name)
public class IncomeTransaction extends Transaction {

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private IncomeCategory category;
}
