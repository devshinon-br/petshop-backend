package com.petshop.trabalho.pet;

import java.io.IOException;
import java.util.List;

public interface PetService {
    Pet save(PetDTO petDTO) throws IOException;

    List<Pet> getAll();

    Pet findById(Long id);

    Pet delete(Long id);
}
