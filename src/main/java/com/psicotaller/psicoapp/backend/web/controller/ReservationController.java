package com.psicotaller.psicoapp.backend.web.controller;

import com.psicotaller.psicoapp.backend.domain.ReservationService;
import com.psicotaller.psicoapp.backend.domain.dto.ReservationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class ReservationController {

    @Autowired
    ReservationService service;

    @GetMapping("/reservations")
    public List<ReservationDto> getReservations() {return service.findAll();}
}
