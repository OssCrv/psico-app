package com.psicotaller.psicoapp.backend.domain.mapper;

import com.psicotaller.psicoapp.backend.domain.dto.ReservationDto;
import com.psicotaller.psicoapp.backend.persistence.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring",
        uses = {
        FacilityMapper.class,
        TherapistMapper.class
        })
public interface ReservationMapper extends EntityMapper<ReservationDto, Reservation> {

    @Override
    @Mappings({
            @Mapping(target = "therapist", source = "therapistDto"),
            @Mapping(target = "facility", source = "facilityDto"),
            @Mapping(target = "dateCreation", ignore = true),
            @Mapping(target = "lastUpdate", ignore = true)

    })
    Reservation toEntity(ReservationDto dto);

    @Override
    @Mappings({
            @Mapping(target = "therapistDto", source = "therapist"),
            @Mapping(target = "facilityDto", source = "facility")
    })
    ReservationDto toDto(Reservation reservation);
}
