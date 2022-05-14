package com.psicotaller.psicoapp.backend.domain.impl;

import com.psicotaller.psicoapp.backend.domain.ReservationService;
import com.psicotaller.psicoapp.backend.domain.dto.ReservationDto;
import com.psicotaller.psicoapp.backend.domain.mapper.ReservationMapper;
import com.psicotaller.psicoapp.backend.persistence.jpa.ReservationJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationJpaRepository jpaRepository;

    @Autowired
    private ReservationMapper mapper;


    @Override
    public ReservationDto save(ReservationDto dto) {
        return null;
    }

    @Override
    public ReservationDto partialUpdate(ReservationDto dto) {
        return null;
    }

    @Override
    public List<ReservationDto> findAll() {
        log.debug("Request to get all Reservations");
        return mapper.toDto(jpaRepository.findAll());
    }

    @Override
    public ReservationDto findOne(Integer id) {
        return null;
    }

    @Override
    public Boolean delete(Integer id) {
        return null;
    }
}
