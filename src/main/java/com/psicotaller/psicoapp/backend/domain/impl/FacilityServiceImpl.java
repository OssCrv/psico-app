package com.psicotaller.psicoapp.backend.domain.impl;

import com.psicotaller.psicoapp.backend.domain.dto.FacilityDto;
import com.psicotaller.psicoapp.backend.domain.exception.ResourceNotFoundException;
import com.psicotaller.psicoapp.backend.domain.mapper.FacilityMapper;
import com.psicotaller.psicoapp.backend.persistence.Building;
import com.psicotaller.psicoapp.backend.persistence.Facility;
import com.psicotaller.psicoapp.backend.persistence.jpa.BuildingJpaRepository;
import com.psicotaller.psicoapp.backend.persistence.jpa.FacilityJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class FacilityServiceImpl {

    @Autowired
    private FacilityJpaRepository jpaRepository;

    @Autowired
    private FacilityMapper mapper;

    @Autowired
    private BuildingJpaRepository buildingRepository;

    public List<FacilityDto> findAll() {return mapper.toDto(jpaRepository.findAll());}

    public FacilityDto findById(Integer id){
        log.info("Cuarto encontrado");
        return mapper.toDto(
                jpaRepository.findById(id)
                        .orElseThrow(
                        () -> new ResourceNotFoundException("Facility", "Id", id.toString())
                )
        );
    }

    public FacilityDto save(FacilityDto dto){
        log.info(dto.toString());
        Facility facility = jpaRepository.save(mapper.toEntity(dto));
        log.info(facility.toString());
        log.info("Localización guardada");
        return mapper.toDto(facility);
    }

    public void delete(Integer id){
        log.info("Cuarto eliminado");
        jpaRepository.deleteById(id);
    }

    public void updateFacility(FacilityDto dto){
        Facility facility = jpaRepository.findById(dto.getId())
                .orElseThrow( () ->
                        new ResourceNotFoundException("Facility", "Id", dto.getId().toString())
                );
    }
}
