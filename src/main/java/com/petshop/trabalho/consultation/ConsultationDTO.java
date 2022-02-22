package com.petshop.trabalho.consultation;

import lombok.Data;

import java.util.Date;

@Data
public class ConsultationDTO {
    private Date date;
    private String description;
    private Long pet_id;
}
