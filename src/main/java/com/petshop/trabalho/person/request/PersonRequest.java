package com.petshop.trabalho.person.request;

import lombok.Data;

@Data
public class PersonRequest {
    private String name;
    private String street;
    private Integer number;
    private String district;
    private String city;
}
