package com.petshop.trabalho.consultation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
    Set<Consultation>  findAllByPetId(Long petId);
}
