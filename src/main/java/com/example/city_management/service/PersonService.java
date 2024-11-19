package com.example.city_management.service;

import com.example.city_management.dto.PersonDTO;
import com.example.city_management.entity.Person;

import java.util.List;

public interface PersonService {

    PersonDTO createPerson(PersonDTO personDTO);

    List<PersonDTO> getAllPersons();

    PersonDTO getPersonById(Long id);

    PersonDTO updatePerson(Long id, PersonDTO personDetails);

    void deletePerson(Long id);
}
