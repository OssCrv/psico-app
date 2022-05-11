package com.psicotaller.psicoapp.backend.web.controller;

import com.psicotaller.psicoapp.backend.domain.impl.BuildingServiceImpl;
import com.psicotaller.psicoapp.backend.domain.dto.BuildingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class BuildingController {

    @Autowired
    private BuildingServiceImpl service;

    @GetMapping("/buildings")
    public List<BuildingDto> getBuildings(){
        return service.findAll();
    }

    @GetMapping("/buildings/{buildingId}")
    public BuildingDto getBuilding(@PathVariable Integer buildingId){
            return service.findOne(buildingId);
    }

    @PostMapping("/buildings")
    public BuildingDto save(@RequestBody BuildingDto dto){
        return service.save(dto);
    }

    @PutMapping("/buildings")
    public BuildingDto update(@RequestBody BuildingDto dto){
        return service.partialUpdate(dto);
    }

    @DeleteMapping("/buildings/{buildingId}")
    public Boolean delete(@PathVariable Integer buildingId){
        return service.delete(buildingId);
    }


}
