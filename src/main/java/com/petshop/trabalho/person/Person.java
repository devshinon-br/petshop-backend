package com.petshop.trabalho.person;

import com.petshop.trabalho.pet.Pet;
import com.petshop.trabalho.veterinary.Veterinary;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name="person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String street;

    private Integer number;

    private String district;

    private String city;

    @OneToOne(mappedBy="person", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Veterinary veterinary;

    @ManyToMany
    @JoinTable(
        name="person_pet",
        joinColumns = @JoinColumn(name="person_id"),
        inverseJoinColumns = @JoinColumn(name="pet_id"))
    private Set<Pet> person_pet;
}
