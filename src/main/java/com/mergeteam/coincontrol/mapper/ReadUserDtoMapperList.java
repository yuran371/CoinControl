package com.mergeteam.coincontrol.mapper;

import com.mergeteam.coincontrol.dto.ReadUserDto;
import com.mergeteam.coincontrol.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public abstract class ReadUserDtoMapperList {
    public static final ReadUserDtoMapperList INSTANCE = Mappers.getMapper(ReadUserDtoMapperList.class);


    @Mapping(target = "id", source = "id")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "avatarPath", source = "avatarPath")
    @Mapping(target = "roles", source = "roles")
    public abstract List<ReadUserDto> map(List<User> user);

}
