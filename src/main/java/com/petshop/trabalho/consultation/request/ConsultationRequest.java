package com.petshop.trabalho.consultation.request;

import lombok.Data;

import java.util.Date;

@Data
public class ConsultationRequest {
    private Date date;
    private String description;
}
