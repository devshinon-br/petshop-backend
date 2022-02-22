package com.petshop.trabalho.person;

import java.io.IOException;
import java.util.List;

public interface PersonService {
    Person save(PersonDTO personDTO) throws IOException;

    List<Person> getAll();

    Person findById(Long id);

    Person delete(Long id);
}
