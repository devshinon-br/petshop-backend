package com.petshop.trabalho.veterinary;

import com.petshop.trabalho.consultation.Consultation;
import com.petshop.trabalho.person.Person;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name="veterinary")
public class Veterinary {
    @Id
    @Column(name="person_id")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name="person_id")
    private Person person;

    private String crmv;

    @ManyToMany
    @JoinTable(
            name="veterinary_consultation",
            joinColumns = @JoinColumn(name="veterinary_id"),
            inverseJoinColumns = @JoinColumn(name="consultation_id"))
    private Set<Consultation> vet_consultation;

}
