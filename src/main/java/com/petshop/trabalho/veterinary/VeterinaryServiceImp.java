package com.petshop.trabalho.veterinary;

import com.petshop.trabalho.person.PersonRepository;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class VeterinaryServiceImp implements VeterinaryService{
    private final VeterinaryRepository veterinaryRepository;
    private final PersonRepository personRepository;

    public VeterinaryServiceImp(VeterinaryRepository veterinaryRepository, PersonRepository personRepository) {
        this.veterinaryRepository = veterinaryRepository;
        this.personRepository = personRepository;
    }

    @Override
    public Veterinary save(VeterinaryDTO veterinaryDTO) throws IOException {
        Veterinary veterinary = new Veterinary();

        veterinary.setCrmv(veterinaryDTO.getCrmv());
        veterinary.setPerson(personRepository.findById(veterinaryDTO.getPerson_id()).get());

        return veterinaryRepository.save(veterinary);
    }

    @Override
    public List<Veterinary> getAll() {
        return veterinaryRepository.findAll();
    }

    @Override
    public Veterinary findById(Long id) {
        return veterinaryRepository.findById(id).get();
    }

    @Override
    public Veterinary delete(Long id) {
        Veterinary veterinary = null;

        if(Objects.nonNull(findById(id))){
            veterinary = findById(id);
        }

        if(Objects.nonNull(veterinary)){
            veterinaryRepository.delete(veterinary);
        }

        return veterinary;
    }
}
