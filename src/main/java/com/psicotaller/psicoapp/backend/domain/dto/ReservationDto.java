package com.psicotaller.psicoapp.backend.domain.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Date;

@Data
@ToString
public class ReservationDto implements Serializable {
    private Integer id;
    private Integer fkFacility;
    private Integer fkTherapist;
    private Date reservationDate;
    private FacilityDto facilityDto;
    private TherapistDto therapistDto;
}
