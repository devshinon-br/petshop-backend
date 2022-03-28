package com.petshop.trabalho.person;

import com.petshop.trabalho.person.request.PersonRequest;

import java.io.IOException;
import java.util.List;

public interface PersonService {
    Person save(PersonDTO personDTO) throws IOException;

    Person update(Long id, PersonRequest personRequest);

    List<Person> getAll();

    Person findById(Long id);

    Person delete(Long id);
}
