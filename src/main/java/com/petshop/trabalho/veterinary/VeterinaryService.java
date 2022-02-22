package com.petshop.trabalho.veterinary;

import java.io.IOException;
import java.util.List;

public interface VeterinaryService {
    Veterinary save(VeterinaryDTO veterinaryDTO) throws IOException;

    List<Veterinary> getAll();

    Veterinary findById(Long id);

    Veterinary delete(Long id);
}
