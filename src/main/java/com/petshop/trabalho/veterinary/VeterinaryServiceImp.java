package com.petshop.trabalho.veterinary;

import com.petshop.trabalho.person.Person;
import com.petshop.trabalho.person.PersonRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
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
        Optional<Person> person = personRepository.findById(veterinaryDTO.getPerson_id());

        if(person.isPresent()){
            veterinary.setCrmv(veterinaryDTO.getCrmv());
            veterinary.setPerson(person.get());

            veterinaryRepository.save(veterinary);
        }

        return veterinary;
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
