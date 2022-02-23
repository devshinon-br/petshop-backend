package com.petshop.trabalho.pet;

import com.petshop.trabalho.person.Person;
import lombok.Data;
import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name="pet")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "color", nullable = false)
    private String color;

    @Column(name = "birth_date", nullable = false)
    private Date birth_date;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToMany
    @JoinTable(
            name="person_pet",
            joinColumns = @JoinColumn(name="pet_id"),
            inverseJoinColumns = @JoinColumn(name="person_id"))
    private Set<Person> pet_person = new LinkedHashSet<>();
}
