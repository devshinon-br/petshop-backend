package com.petshop.trabalho.pet.request;

import lombok.Data;

import java.util.Date;

@Data
public class PetRequest {
    private String name;
    private String color;
    private Date birth_date;
    private String description;
}
