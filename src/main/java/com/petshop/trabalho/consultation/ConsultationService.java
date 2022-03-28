package com.petshop.trabalho.consultation;

import com.petshop.trabalho.consultation.request.ConsultationRequest;

import java.io.IOException;
import java.util.List;

public interface ConsultationService {
    Consultation save(ConsultationDTO consultationDTO) throws IOException;

    Consultation update(Long id, ConsultationRequest consultationRequest);

    List<Consultation> getAll();

    Consultation findById(Long id);

    Consultation delete(Long id);
}
