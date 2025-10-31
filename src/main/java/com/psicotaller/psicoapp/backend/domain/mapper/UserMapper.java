package com.psicotaller.psicoapp.backend.domain.mapper;

import com.psicotaller.psicoapp.backend.domain.dto.UserDto;
import com.psicotaller.psicoapp.backend.persistence.UserApp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "role", source = "rol")
    UserDto toDto(UserApp entity);

    List<UserDto> toDto(List<UserApp> entities);
}
