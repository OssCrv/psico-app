package com.psicotaller.psicoapp.backend.domain.mapper;

import com.psicotaller.psicoapp.backend.domain.dto.BuildingDto;
import com.psicotaller.psicoapp.backend.domain.dto.FacilityDto;
import com.psicotaller.psicoapp.backend.persistence.Building;
import com.psicotaller.psicoapp.backend.persistence.Facility;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {BuildingMapper.class})
public interface FacilityMapper extends EntityMapper<FacilityDto, Facility>{

    @Override
    @Mappings({
            @Mapping(target = "building", source = "buildingDto"),
            @Mapping(target = "reservations", ignore = true)
    })
    Facility toEntity(FacilityDto dto);

    @Override
    @Mapping(
            target = "buildingDto", source = "building")
    FacilityDto toDto(Facility facility);

}
