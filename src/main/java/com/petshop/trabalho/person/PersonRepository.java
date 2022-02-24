package com.petshop.trabalho.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Collection<Person> findByIdIn( Collection<Long> longSet);
}
