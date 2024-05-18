package com.mergeteam.coincontrol.api.sort;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;

@Getter
@RequiredArgsConstructor
public enum TransactionSort {

    AMOUNT_ASC(Sort.by(Sort.Direction.ASC, "amount")),
    AMOUNT_DESC(Sort.by(Sort.Direction.DESC, "amount")),
    DATE_ASC(Sort.by(Sort.Direction.ASC, "date")),
    DATE_DESC(Sort.by(Sort.Direction.DESC, "date")),
    CATEGORY_ASC(Sort.by(Sort.Direction.ASC, "category")),
    CATEGORY_DESC(Sort.by(Sort.Direction.DESC, "category")),
    NAME_DESC(Sort.by(Sort.Direction.DESC, "name")),
    NAME_ASC(Sort.by(Sort.Direction.ASC, "name"))
    ;

    private final Sort sortValue;
}
