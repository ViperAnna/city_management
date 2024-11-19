package com.example.city_management.controller;


import com.example.city_management.dto.PersonDTO;
import com.example.city_management.service.Impl.PersonServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
@AllArgsConstructor
public class PersonController {

    private final PersonServiceImpl personServiceImpl;

    @PostMapping
    public ResponseEntity<PersonDTO> createPerson(@RequestBody PersonDTO personDTO) {
        PersonDTO person = personServiceImpl.createPerson(personDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(person);
    }

    @GetMapping
    public ResponseEntity<List<PersonDTO>> getAllPersons() {
        List<PersonDTO> personsDto = personServiceImpl.getAllPersons();
        return ResponseEntity
                .ok(personsDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> getPersonById(@PathVariable Long id) {
        PersonDTO person = personServiceImpl.getPersonById(id);
        return ResponseEntity
                .ok(person);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonDTO> updatePerson(@PathVariable Long id, @RequestBody PersonDTO personDetails) {
        PersonDTO person = personServiceImpl.updatePerson(id, personDetails);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(person);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        personServiceImpl.deletePerson(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}


