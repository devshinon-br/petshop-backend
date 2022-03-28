package com.petshop.trabalho.pet;

import com.petshop.trabalho.pet.request.PetRequest;

import java.io.IOException;
import java.util.List;

public interface PetService {
    Pet save(PetDTO petDTO) throws IOException;

    Pet updateCharacteristics(Long id, PetRequest petRequest);

    List<Pet> getAll();

    Pet findById(Long id);

    Pet delete(Long id);
}
