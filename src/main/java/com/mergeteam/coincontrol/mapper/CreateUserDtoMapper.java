package com.mergeteam.coincontrol.mapper;

import com.mergeteam.coincontrol.dto.CreateUserDto;
import com.mergeteam.coincontrol.entity.User;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public abstract class CreateUserDtoMapper {

    @Autowired
    protected PasswordEncoder passwordEncoder;

    public static final CreateUserDtoMapper INSTANCE = Mappers.getMapper(CreateUserDtoMapper.class);

    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", source = "password", qualifiedByName = "mapPassword")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "avatarPath", source = "avatarPath")
    @Mapping(target = "role", source = "role")
    public abstract User dtoToEntity(CreateUserDto createUserDto);

    @Named("mapPassword")
    String mapPassword(String password) {
        return passwordEncoder.encode(password);
    }

}
