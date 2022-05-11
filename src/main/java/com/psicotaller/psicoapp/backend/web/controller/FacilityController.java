package com.psicotaller.psicoapp.backend.web.controller;

import com.psicotaller.psicoapp.backend.domain.impl.FacilityServiceImpl;
import com.psicotaller.psicoapp.backend.domain.dto.FacilityDto;
import com.psicotaller.psicoapp.backend.domain.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class FacilityController {

    @Autowired
    FacilityServiceImpl service;

    @GetMapping("/facilities")
    public List<FacilityDto> getFacilities(){
        return service.findAll();
    }

    @GetMapping("/facilities/{facilityId}")
    public FacilityDto getFacility(@PathVariable Integer facilityId){
        try{
            return service.findById(facilityId);
        } catch (ResourceNotFoundException e){
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, "Facility not Found", e);
        }
    }

    @PostMapping("/facilities")
    public FacilityDto save(@RequestBody FacilityDto dto){
        return service.save(dto);
    }
}
