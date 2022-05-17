package com.psicotaller.psicoapp.backend.domain.impl;

import com.psicotaller.psicoapp.backend.domain.ReservationService;
import com.psicotaller.psicoapp.backend.domain.dto.ReservationDto;
import com.psicotaller.psicoapp.backend.domain.exception.ResourceNotFoundException;
import com.psicotaller.psicoapp.backend.domain.mapper.ReservationMapper;
import com.psicotaller.psicoapp.backend.persistence.Facility;
import com.psicotaller.psicoapp.backend.persistence.Reservation;
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
        log.debug("Request to save Reservation : {}", dto);
        Reservation reservation = mapper.toEntity(dto);
        reservation = jpaRepository.save(reservation);
        return mapper.toDto(reservation);
    }

    @Override
    public ReservationDto partialUpdate(ReservationDto dto) {
        log.info("Request to partially update Reservation : {}", dto);
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
                        new ResourceNotFoundException("Reservation", "id", id.toString())
                );
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservationDto> findAll() {
        log.debug("Request to get all Reservations");
        return mapper.toDto(jpaRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public ReservationDto findOne(Integer id) {
        log.debug("Request to get Reservation : {}", id);
        return mapper.toDto(
                jpaRepository.findById(id)
                        .orElseThrow(
                                () -> new ResourceNotFoundException("Reservation", "Id", id.toString())
                        )
        );
    }

    @Override
    public Boolean delete(Integer id) {
        log.info("Reservacion eliminada : {}", id);
        return jpaRepository.findById(id)
                .map(building -> {
                    jpaRepository.deleteById(id);
                    return true;
                })
                .orElse(false);
    }
}
