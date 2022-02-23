package com.petshop.trabalho.consultation;

import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class ConsultationDTO {
    private Date date;
    private String description;
    private Long pet_id;
    private Set<Long> vet_id;
}
