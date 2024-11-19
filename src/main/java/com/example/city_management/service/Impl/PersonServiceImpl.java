package com.example.city_management.service.Impl;

import com.example.city_management.dto.PersonDTO;
import com.example.city_management.entity.Car;
import com.example.city_management.entity.House;
import com.example.city_management.entity.Passport;
import com.example.city_management.entity.Person;
import com.example.city_management.entity.enumeration.Gender;
import com.example.city_management.exception.PersonNotFoundException;
import com.example.city_management.repository.CarRepository;
import com.example.city_management.repository.HouseRepository;
import com.example.city_management.repository.PassportRepository;
import com.example.city_management.repository.PersonRepository;
import com.example.city_management.mapper.PersonMapper;
import com.example.city_management.service.PersonService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final CarRepository carRepository;
    private final HouseRepository houseRepository;

    private final PassportRepository passportRepository;

    @Transactional
    public PersonDTO createPerson(@NonNull PersonDTO personDetails) {
        Person person = new Person();
        person.setName(personDetails.getName());
        person.setSurname(personDetails.getSurname());
        person.setGender(PersonMapper.INSTANCE.stringToGender(personDetails.getGender()));
        person.setBalance(BigDecimal.ZERO);
        person = personRepository.save(person);

        Passport passport = new Passport();
        passport.setNumberOfPassport(personDetails.getNumberOfPassport());
        passport.setOwner(person);
        passportRepository.save(passport);

        return PersonMapper.INSTANCE.toDto(personRepository.save(person));
    }

    public List<PersonDTO> getAllPersons() {
        List<Person> personList = (List<Person>) personRepository.findAll();
        return personList.stream()
                .map(PersonMapper.INSTANCE::toDto)
                .toList();
    }

    public PersonDTO getPersonById(Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() ->
                        new PersonNotFoundException("Person not found with id: " + id));
        return PersonMapper.INSTANCE.toDto(person);
    }

    public List<Person> getAllOwnersByStreet(String street) {
        return personRepository.getAllOwnersByStreet("%" + street + "%");
    }

    @Transactional
    public PersonDTO updatePerson(Long id, @NonNull PersonDTO personDetails) {
        Person person = personRepository.findById(id)
                .orElseThrow(() ->
                        new PersonNotFoundException("Person not found with id: " + id));
        person.setName(personDetails.getName());
        person.setSurname(personDetails.getSurname());
        person.setGender(PersonMapper.INSTANCE.stringToGender(personDetails.getGender()));
        person.setBalance(personDetails.getBalance());
        return PersonMapper.INSTANCE.toDto(personRepository.save(person));
    }

    @Transactional
    public void deletePerson(Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException("Person not found with id: " + id));

        for (House house : person.getHouses()) {
            house.getOwners().remove(person);
            houseRepository.save(house);
        }

        Set<Car> cars = person.getCars();
        for (Car car : cars) {
            car.setOwner(null);
            carRepository.save(car);
        }

        carRepository.deleteAll(cars);
        personRepository.delete(person);
    }

}

