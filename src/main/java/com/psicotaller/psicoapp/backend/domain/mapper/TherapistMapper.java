package com.psicotaller.psicoapp.backend.domain.mapper;

import com.psicotaller.psicoapp.backend.domain.dto.TherapistDto;
import com.psicotaller.psicoapp.backend.persistence.UserApp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface TherapistMapper extends EntityMapper<TherapistDto, UserApp> {
    @Override
    @Mappings({
            @Mapping(target = "pass", ignore = true),
            @Mapping(target = "lastName", ignore = true),
            @Mapping(target = "rol", ignore = true),
            @Mapping(target = "reservations", ignore = true)
    })
    UserApp toEntity(TherapistDto dto);
}
