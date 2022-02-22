package com.petshop.trabalho.pet;

import lombok.Data;

import java.util.Date;

@Data
public class PetDTO {
    private String name;
    private String color;
    private Date birth_date;
    private String description;
}
