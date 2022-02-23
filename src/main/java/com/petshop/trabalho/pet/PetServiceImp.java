package com.petshop.trabalho.pet;

import com.petshop.trabalho.person.Person;
import com.petshop.trabalho.person.PersonRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PetServiceImp implements PetService{

    private final PetRepository petRepository;
    private final PersonRepository personRepository;

    public PetServiceImp(PetRepository petRepository, PersonRepository personRepository) {
        this.petRepository = petRepository;
        this.personRepository = personRepository;
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

        if(Objects.nonNull(findById(id))){
            pet = findById(id);
        }

        if(Objects.nonNull(pet)){
            petRepository.delete(pet);
        }

        return pet;
    }
}
