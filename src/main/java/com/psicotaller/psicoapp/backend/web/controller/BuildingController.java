package com.psicotaller.psicoapp.backend.web.controller;

import com.psicotaller.psicoapp.backend.domain.BuildingService;
import com.psicotaller.psicoapp.backend.domain.dto.BuildingDto;
import com.psicotaller.psicoapp.backend.domain.exception.ResourceNotFoundException;
import com.psicotaller.psicoapp.backend.persistence.Building;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/buildings")
public class BuildingController {

    @Autowired
    private BuildingService service;

    @GetMapping("/")
    public List<BuildingDto> getBuildings(){
        return service.findAll();
    }

    @GetMapping("/{buildingId}")
    public Building getBuilding(@PathVariable Integer buildingId){
        try {
            return service.findById(buildingId);
        }catch (ResourceNotFoundException e){
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, "Building not Found", e);
        }
    }

    @PostMapping("/save")
    public BuildingDto save(@RequestBody BuildingDto building){
        return service.save(building);
    }


}
