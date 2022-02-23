package com.petshop.trabalho.veterinary;

import com.petshop.trabalho.person.Person;
import lombok.Data;
import javax.persistence.*;

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
