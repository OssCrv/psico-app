package com.psicotaller.psicoapp.backend.domain.impl;

import com.psicotaller.psicoapp.backend.domain.BuildingService;
import com.psicotaller.psicoapp.backend.domain.ResourceService;
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

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private BuildingJpaRepository jpaRepository;

    @Autowired
    private BuildingMapper mapper;

    @Override
    public BuildingDto save(BuildingDto dto) {
        log.debug("Request to save Building : {}", dto);
        Building building = mapper.toEntity(dto);
        building = jpaRepository.save(building);
        return mapper.toDto(building);
    }

    @Override
    public BuildingDto partialUpdate(BuildingDto dto) {
        log.debug("Request to partially update Building : {}", dto);
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
                        new ResourceNotFoundException("Building", "id", id.toString())
                );
    }

    @Override
    @Transactional(readOnly = true)
    public List<BuildingDto> findAll() {
        log.debug("Request to get all Buildings");
        return jpaRepository.findAll()
                .stream().map(mapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public BuildingDto findOne(Integer id) {
        log.debug("Request to get Buildings : {}", id);
        return jpaRepository.findById(id).map(mapper::toDto)
                .orElseThrow( () ->
                        new ResourceNotFoundException("Building", "id", id.toString())
                );
    }

    @Override
    public Boolean delete(Integer id) {
        log.info("Request to delete Building : {}", id);
        return jpaRepository.findById(id)
                .map(building -> {
                    jpaRepository.deleteById(id);
                    return true;
                })
                .orElse(false);
    }
}
