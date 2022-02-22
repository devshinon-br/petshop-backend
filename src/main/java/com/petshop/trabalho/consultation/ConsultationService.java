package com.petshop.trabalho.consultation;

import java.io.IOException;
import java.util.List;

public interface ConsultationService {
    Consultation save(ConsultationDTO consultationDTO) throws IOException;

    List<Consultation> getAll();

    Consultation findById(Long id);

    Consultation delete(Long id);
}
