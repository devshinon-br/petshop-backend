package com.petshop.trabalho.pet;

import com.petshop.trabalho.consultation.Consultation;
import com.petshop.trabalho.person.Person;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name="pet")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String color;

    private Date birth_date;

    private String description;

    @OneToMany(mappedBy="pet")
    private Set<Consultation> consultations;

    @ManyToMany(mappedBy="person_pet")
    private Set<Person> pet_person;
}
