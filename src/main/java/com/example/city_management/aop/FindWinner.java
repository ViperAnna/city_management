package com.example.city_management.aop;

import com.example.city_management.entity.Person;
import com.example.city_management.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@RequiredArgsConstructor
public class FindWinner {
    private final PersonRepository personRepository;
    private final Random random = new Random();
    @RobbinGood
    public Person findWinner() {
        long count = personRepository.count();
        if (count == 0) return null;
        long randomId = random.nextInt((int) count) + 1;
        return personRepository.findById(randomId).orElse(null);
    }
}
