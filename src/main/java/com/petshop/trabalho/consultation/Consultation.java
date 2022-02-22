package com.petshop.trabalho.consultation;

import com.petshop.trabalho.pet.Pet;
import com.petshop.trabalho.veterinary.Veterinary;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name="consultation")
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private Date date;

    @ManyToOne
    @JoinColumn(name="pet_id", nullable=false)
    private Pet pet;

    @ManyToMany(mappedBy="vet_consultation")
    private Set<Veterinary> consultation_vet;
}
