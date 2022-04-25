package com.psicotaller.psicoapp.backend.domain;

import com.psicotaller.psicoapp.backend.persistence.entities.Building;
import com.psicotaller.psicoapp.backend.persistence.entities.Facility;
import com.psicotaller.psicoapp.backend.persistence.entities.Reservation;
import com.psicotaller.psicoapp.backend.persistence.jpa.BuildingJpaRepository;
import com.psicotaller.psicoapp.backend.persistence.jpa.FacilityJpaRepository;
import com.psicotaller.psicoapp.backend.persistence.jpa.ReservationJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntitiesProviderServices {

    @Autowired
    private BuildingJpaRepository buildingJpaRepository;

    @Autowired
    private FacilityJpaRepository facilityJpaRepository;

    @Autowired
    private ReservationJpaRepository reservationJpaRepository;

    public List<Building> getAllBuildings(){
        return buildingJpaRepository.findAll();
    }

    public List<Facility> getAllFacilities(){
        return facilityJpaRepository.findAll();
    }

    public List<Reservation> getAllReservations(){
        return reservationJpaRepository.findAll();
    }

}
