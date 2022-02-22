package com.petshop.trabalho.pet;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class PetServiceImp implements PetService{

    private final PetRepository petRepository;

    public PetServiceImp(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public Pet save(PetDTO petDTO) throws IOException {
        Pet pet = new Pet();

        pet.setName(petDTO.getName());
        pet.setColor(petDTO.getColor());
        pet.setBirth_date(petDTO.getBirth_date());
        pet.setDescription(petDTO.getDescription());

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
