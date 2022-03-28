package com.petshop.trabalho.veterinary;

import com.petshop.trabalho.veterinary.request.VeterinaryRequest;

import java.io.IOException;
import java.util.List;

public interface VeterinaryService {
    Veterinary save(VeterinaryDTO veterinaryDTO) throws IOException;

    Veterinary updateCrmv(Long id, VeterinaryRequest veterinaryRequest);

    List<Veterinary> getAll();

    Veterinary findById(Long id);

    Veterinary delete(Long id);
}
