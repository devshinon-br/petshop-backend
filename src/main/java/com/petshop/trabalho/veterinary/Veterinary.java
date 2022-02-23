package com.petshop.trabalho.veterinary;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @MapsId
    @OneToOne
    @PrimaryKeyJoinColumn(name="person_id")
    private Person person;

    @Column(name = "crmv", nullable = false)
    private String crmv;
}
