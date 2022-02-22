package com.petshop.trabalho.consultation;

import com.petshop.trabalho.consultation.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
}
