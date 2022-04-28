package com.psicotaller.psicoapp.backend.domain.mapper;

import com.psicotaller.psicoapp.backend.domain.dto.BuildingDto;
import com.psicotaller.psicoapp.backend.persistence.Building;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BuildingMapper {

    BuildingDto toDTO(Building building);
    List<BuildingDto> toDTOs(List<Building> buildings);

    @InheritInverseConfiguration
    @Mapping(target = "facilities", ignore = true)
    Building toDAO(BuildingDto buildingDto);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
                    ignoreUnmappedSourceProperties = {"facilities"})
    void updateBuildingFromDto(BuildingDto dto, @MappingTarget Building building);
}
