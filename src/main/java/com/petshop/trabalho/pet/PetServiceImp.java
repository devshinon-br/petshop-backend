package com.petshop.trabalho.pet;

import com.petshop.trabalho.consultation.Consultation;
import com.petshop.trabalho.consultation.ConsultationRepository;
import com.petshop.trabalho.person.Person;
import com.petshop.trabalho.person.PersonRepository;
import com.petshop.trabalho.pet.request.PetRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class PetServiceImp implements PetService{

    private final PetRepository petRepository;
    private final PersonRepository personRepository;
    private final ConsultationRepository consultationRepository;

    public PetServiceImp(PetRepository petRepository, PersonRepository personRepository, ConsultationRepository consultationRepository) {
        this.petRepository = petRepository;
        this.personRepository = personRepository;
        this.consultationRepository = consultationRepository;
    }

    @Override
    public Pet save(PetDTO petDTO) throws IOException {
        Pet pet = new Pet();
        Optional<Person> person = personRepository.findById(petDTO.getPerson_id());

        pet.setName(petDTO.getName());
        pet.setColor(petDTO.getColor());
        pet.setBirth_date(petDTO.getBirth_date());
        pet.setDescription(petDTO.getDescription());
        person.ifPresent(pet.getPet_person()::add);

        return petRepository.save(pet);
    }

    @Override
    public Pet updateCharacteristics(Long id, PetRequest petRequest){
        Pet pet = petRepository.findById(id).get();

        if(Objects.nonNull(pet)){
            pet.setName(petRequest.getName());
            pet.setColor(petRequest.getColor());
            pet.setBirth_date(petRequest.getBirth_date());
            pet.setDescription(petRequest.getDescription());
        }

        return petRepository.save(pet);
    }

    @Override
    public List<Pet> getAll() {
        return petRepository.findAll();
    }

    @Override
    public Pet findById(Long id) {
        return petRepository.findById(id).get();
    }

    @Override
    public Pet delete(Long id) {
        Pet pet = null;

        if(petRepository.existsById(id)){
            pet = findById(id);
        }

        if(Objects.nonNull(pet)){
            Set<Consultation> consultations = consultationRepository.findAllByPetId(pet.getId());
            for (Consultation consultation: consultations) {
                consultationRepository.delete(consultation);
            }

            petRepository.delete(pet);
        }

        return pet;
    }
}
