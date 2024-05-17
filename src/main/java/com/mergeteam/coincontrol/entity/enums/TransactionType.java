package com.mergeteam.coincontrol.entity.enums;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TransactionType {

    public class Expense {

        public static final String name = "expense";
        public static final int value = -1;

    }

    public class Income {

        public static final String name = "income";
        public static final int value = 1;

    }

}
