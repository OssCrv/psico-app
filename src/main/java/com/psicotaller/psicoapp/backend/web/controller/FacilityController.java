package com.psicotaller.psicoapp.backend.web.controller;

import com.psicotaller.psicoapp.backend.domain.FacilityService;
import com.psicotaller.psicoapp.backend.domain.dto.FacilityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class FacilityController {

    @Autowired
    FacilityService service;

    @GetMapping("/facilities")
    public List<FacilityDto> getFacilities(){
        return service.findAll();
    }

    @GetMapping("/facilities/{facilityId}")
    public FacilityDto getFacility(@PathVariable Integer facilityId){
        return service.findOne(facilityId);
    }

    @PostMapping("/facilities")
    public FacilityDto save(@RequestBody FacilityDto dto){
        return service.save(dto);
    }

    @PutMapping("/facilities")
    public FacilityDto update(@RequestBody FacilityDto dto){
        return service.partialUpdate(dto);
    }

    @DeleteMapping("/facilities/{facilityId}")
    public Boolean delete(@PathVariable Integer facilityId){
        return service.delete(facilityId);
    }
}
