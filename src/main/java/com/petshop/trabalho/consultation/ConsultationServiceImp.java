package com.petshop.trabalho.consultation;

import com.petshop.trabalho.pet.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class ConsultationServiceImp implements ConsultationService{
    private final ConsultationRepository consultationRepository;
    private final PetRepository petRepository;

    @Autowired
    public ConsultationServiceImp(ConsultationRepository consultationRepository,
                                  PetRepository petRepository){
        this.consultationRepository = consultationRepository;
        this.petRepository = petRepository;
    }

    @Override
    public Consultation save(ConsultationDTO consultationDTO) throws IOException {
        Consultation consultation = new Consultation();

        consultation.setDescription(consultationDTO.getDescription());
        consultation.setDate(consultationDTO.getDate());
        consultation.setPet(petRepository.findById(consultationDTO.getPet_id()).get());

        return consultationRepository.save(consultation);
    }

    @Override
    public List<Consultation> getAll() {
        return consultationRepository.findAll();
    }

    @Override
    public Consultation findById(Long id) {
        return consultationRepository.findById(id).get();
    }

    @Override
    public Consultation delete(Long id) {
        Consultation consultation = null;

        if(Objects.nonNull(findById(id))){
            consultation = findById(id);
        }

        if(Objects.nonNull(consultation)){
            consultationRepository.delete(consultation);
        }

        return consultation;
    }
}
