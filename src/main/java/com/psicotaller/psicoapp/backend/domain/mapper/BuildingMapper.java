package com.psicotaller.psicoapp.backend.domain.mapper;

import com.psicotaller.psicoapp.backend.domain.dto.BuildingDto;
import com.psicotaller.psicoapp.backend.persistence.Building;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface BuildingMapper extends EntityMapper<BuildingDto, Building>{
}
