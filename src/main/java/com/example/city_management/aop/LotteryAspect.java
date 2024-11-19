package com.example.city_management.aop;

import com.example.city_management.dto.PersonDTO;
import com.example.city_management.entity.Car;
import com.example.city_management.entity.Person;
import com.example.city_management.mapper.PersonMapper;
import com.example.city_management.repository.CarRepository;
import com.example.city_management.service.Impl.CarServiceImpl;
import com.example.city_management.service.Impl.LotteryServiceIml;
import com.example.city_management.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;


@Component
@Aspect
@RequiredArgsConstructor
@Slf4j
public class LotteryAspect {

    private final CarRepository carRepository;
    private final CarServiceImpl carService;
    private final LotteryServiceIml lotteryService;

    private final PersonService personService;
    private final Random random = new Random();


    @Around(value = "@annotation(com.example.city_management.aop.RobbinGood)", argNames = "joinPoint")
    public Object checkWinnerHasCar(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Аспект начал работу");
        Person winner = (Person) joinPoint.proceed();
        boolean hasCar = carService.hasCarByPersonId(winner.getId());//getCars()

        if (hasCar) {
            List<PersonDTO> persons = personService.getAllPersons();
            PersonDTO newWinner = chooseNewWinner(persons, winner);
            if (newWinner != null) {
                winner = PersonMapper.INSTANCE.toEntity(newWinner);
            }
            else {
                return null;
            }
        }
        return winner;
    }

    private PersonDTO chooseNewWinner(List<PersonDTO> persons, Person currentWinner) {
        List<PersonDTO> candidates = persons.stream()
                .filter(person -> !carService.hasCarByPersonId(person.getId()) && !person.equals(currentWinner))
                .toList();
        if (candidates.isEmpty()) {
            return null;
        }
        int index = random.nextInt(candidates.size())+1;
        return candidates.get(index);
    }
}



