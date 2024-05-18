package com.mergeteam.coincontrol.mapper;

import com.mergeteam.coincontrol.dto.ReadExpenseTransactionDto;
import com.mergeteam.coincontrol.entity.ExpenseTransaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public abstract class ReadExpenseTransactionMapper {

    public static ReadExpenseTransactionMapper INSTANCE = Mappers.getMapper(ReadExpenseTransactionMapper.class);

    @Mappings({
            @Mapping(target = "amount", source = "amount"),
            @Mapping(target = "date", source = "date"),
            @Mapping(target = "category", source = "category"),
            @Mapping(target = "walletName", source = "walletId.name"),
    })
    public abstract ReadExpenseTransactionDto entityToDto(ExpenseTransaction expenseTransaction);

}
