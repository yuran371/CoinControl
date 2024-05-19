package com.mergeteam.coincontrol.mapper;

import com.mergeteam.coincontrol.dto.ReadExpenseTransactionDto;
import com.mergeteam.coincontrol.dto.ReadIncomeTransactionDto;
import com.mergeteam.coincontrol.entity.ExpenseTransaction;
import com.mergeteam.coincontrol.entity.IncomeTransaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public abstract class ReadIncomeTransactionMapper {

    public static ReadIncomeTransactionMapper INSTANCE = Mappers.getMapper(ReadIncomeTransactionMapper.class);

    @Mappings({
            @Mapping(target = "amount", source = "amount"),
            @Mapping(target = "date", source = "date"),
            @Mapping(target = "category", source = "category"),
            @Mapping(target = "walletName", source = "walletId.name"),
    })
    public abstract ReadIncomeTransactionDto entityToDto(IncomeTransaction expenseTransaction);

}
