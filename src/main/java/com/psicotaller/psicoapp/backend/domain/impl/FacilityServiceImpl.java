package com.psicotaller.psicoapp.backend.domain.impl;

import com.psicotaller.psicoapp.backend.domain.FacilityService;
import com.psicotaller.psicoapp.backend.domain.dto.FacilityDto;
import com.psicotaller.psicoapp.backend.domain.exception.ResourceNotFoundException;
import com.psicotaller.psicoapp.backend.domain.mapper.FacilityMapper;
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
public class FacilityServiceImpl implements FacilityService {

    @Autowired
    private FacilityJpaRepository jpaRepository;

    @Autowired
    private FacilityMapper mapper;

    @Autowired
    private BuildingJpaRepository buildingRepository;

    @Override
    public FacilityDto save(FacilityDto dto){
        log.info("Request to save Facility : {}", dto);
        Facility facility = mapper.toEntity(dto);
        facility = jpaRepository.save(facility);
        return mapper.toDto(facility);
    }

    @Override
    public FacilityDto partialUpdate(FacilityDto dto){
        log.info("Request to partially update Facility : {}", dto);
        Integer id = dto.getId();
        return jpaRepository
                .findById(id)
                .map(existing -> {
                    mapper.partialUpdate(existing, dto);
                    return existing;
                })
                .map(jpaRepository::save)
                .map(mapper::toDto)
                .orElseThrow( () ->
                        new ResourceNotFoundException("Facility", "id", id.toString())
                );
    }

    @Override
    @Transactional(readOnly = true)
    public List<FacilityDto> findAll() {
        log.debug("Request to get all Facilities");
        return mapper.toDto(jpaRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public FacilityDto findOne(Integer id){
        log.debug("Request to get Facility : {}", id);
        return mapper.toDto(
                jpaRepository.findById(id)
                        .orElseThrow(
                        () -> new ResourceNotFoundException("Facility", "Id", id.toString())
                )
        );
    }

    @Override
    public Boolean delete(Integer id){
        log.info("Cuarto eliminado");
        return jpaRepository.findById(id)
                .map(building -> {
                    jpaRepository.deleteById(id);
                    return true;
                })
                .orElse(false);
    }
}
