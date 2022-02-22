package com.petshop.trabalho.person;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class PersonServiceImp implements PersonService{

    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImp(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    @Override
    public Person save(PersonDTO personDTO) throws IOException {
        Person person = new Person();

        person.setName(personDTO.getName());
        person.setCity(personDTO.getCity());
        person.setDistrict(personDTO.getDistrict());
        person.setStreet(personDTO.getStreet());
        person.setNumber(personDTO.getNumber());

        return personRepository.save(person);
    }

    @Override
    public List<Person> getAll() {
        return personRepository.findAll();
    }

    @Override
    public Person findById(Long id) {
        return personRepository.findById(id).get();
    }

    @Override
    public Person delete(Long id) {
        Person person = null;

        if(Objects.nonNull(findById(id))){
            person = findById(id);
        }

        if(Objects.nonNull(person)){
            personRepository.delete(person);
        }

        return person;
    }
}
