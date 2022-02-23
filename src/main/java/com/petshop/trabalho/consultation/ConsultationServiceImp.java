package com.petshop.trabalho.consultation;

import com.petshop.trabalho.person.PersonRepository;
import com.petshop.trabalho.pet.Pet;
import com.petshop.trabalho.pet.PetRepository;
import com.petshop.trabalho.veterinary.Veterinary;
import com.petshop.trabalho.veterinary.VeterinaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class ConsultationServiceImp implements ConsultationService{
    private final ConsultationRepository consultationRepository;
    private final PetRepository petRepository;
    private final VeterinaryRepository veterinaryRepository;
    private final PersonRepository personRepository;

    @Autowired
    public ConsultationServiceImp(ConsultationRepository consultationRepository,
                                  PetRepository petRepository, VeterinaryRepository veterinaryRepository, PersonRepository personRepository){
        this.consultationRepository = consultationRepository;
        this.petRepository = petRepository;
        this.veterinaryRepository = veterinaryRepository;
        this.personRepository = personRepository;
    }

    @Override
    public Consultation save(ConsultationDTO consultationDTO) throws IOException {
        Consultation consultation = new Consultation();
        Optional<Pet> pet = petRepository.findById(consultationDTO.getPet_id());
        Set<Veterinary> veterinaries = veterinaryRepository.findByIdIn(consultationDTO.getVet_id());

        consultation.setDescription(consultationDTO.getDescription());
        consultation.setDate(consultationDTO.getDate());

        veterinaries.forEach(consultation.getConsultation_vet()::add);

        pet.ifPresent(consultation::setPet);

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
