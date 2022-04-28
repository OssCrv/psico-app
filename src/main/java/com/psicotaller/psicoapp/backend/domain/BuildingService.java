package com.psicotaller.psicoapp.backend.domain;

import com.psicotaller.psicoapp.backend.domain.dto.BuildingDto;
import com.psicotaller.psicoapp.backend.domain.exception.ResourceNotFoundException;
import com.psicotaller.psicoapp.backend.domain.mapper.BuildingMapper;
import com.psicotaller.psicoapp.backend.persistence.Building;
import com.psicotaller.psicoapp.backend.persistence.jpa.BuildingJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class BuildingService {

    @Autowired
    private BuildingJpaRepository jpaRepository;

    @Autowired
    private BuildingMapper mapper;

    public List<BuildingDto> findAll() {
        return mapper.toDTOs(jpaRepository.findAll());
    }

    public Building findById(Integer id){
        log.info("Edificio encontrado");
        return jpaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Building", "Id", id.toString())
        );
    }

    public BuildingDto save(BuildingDto dto){

        log.info(dto.toString());
        Building building = jpaRepository.save(mapper.toDAO(dto));
        log.info(building.toString());
        log.info("Edificio guardado");
        return mapper.toDTO(building);
    }

    public void delete(Integer id){
        log.info("Edificio eliminado");
        jpaRepository.deleteById(id);
    }

    public void updateBuilding(BuildingDto dto){
        Building building = jpaRepository.findById(dto.getId())
                .orElseThrow(
                () -> new ResourceNotFoundException("Building", "Id", dto.getId().toString())
        );
    }
}
