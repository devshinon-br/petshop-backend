package com.petshop.trabalho.consultation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.petshop.trabalho.pet.Pet;
import com.petshop.trabalho.veterinary.Veterinary;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@Table(name="consultation")
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "date", nullable = false)
    private Date date;

    @ManyToOne
    @JoinColumn(name="pet_id", nullable=false)
    private Pet pet;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="veterinary_consultation",
            joinColumns = @JoinColumn(name="consultation_id"),
            inverseJoinColumns = @JoinColumn(name="veterinary_id"))
    private Set<Veterinary> consultation_vet = new LinkedHashSet<>();
}
