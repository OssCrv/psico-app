package com.psicotaller.psicoapp.backend.web.controller;

import com.psicotaller.psicoapp.backend.domain.ReservationService;
import com.psicotaller.psicoapp.backend.domain.dto.ReservationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class ReservationController {

    @Autowired
    ReservationService service;

    @GetMapping("/reservations")
    public List<ReservationDto> getReservations() {return service.findAll();}

    @GetMapping("/reservations/{reservationId}")
    public ReservationDto getFacility(@PathVariable Integer reservationId){
        return service.findOne(reservationId);
    }

    @PostMapping("/reservations")
    public ReservationDto save(@RequestBody ReservationDto dto){
        return service.save(dto);
    }

    @PutMapping("/reservations")
    public ReservationDto update(@RequestBody ReservationDto dto){
        return service.partialUpdate(dto);
    }

    @DeleteMapping("/reservations/{reservationId}")
    public Boolean delete(@PathVariable Integer reservationId){
        return service.delete(reservationId);
    }

}
