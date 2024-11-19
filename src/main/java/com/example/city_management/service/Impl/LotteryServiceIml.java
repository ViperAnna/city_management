package com.example.city_management.service.Impl;

import com.example.city_management.aop.FindWinner;
import com.example.city_management.aop.RobbinGood;
import com.example.city_management.dto.PersonDTO;
import com.example.city_management.entity.Person;
import com.example.city_management.mapper.PersonMapper;
import com.example.city_management.repository.PersonRepository;
import com.example.city_management.service.LotteryService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class LotteryServiceIml implements LotteryService {
    private final Random random = new Random();
    private final PersonRepository personRepository;
    private final FindWinner findWinner;


    public Person startLottery() {
        Person winner = findWinner.findWinner();
        if (winner != null) {
            Person awardWinner = awardWinner(winner);
            System.out.println("WINNER: " + awardWinner.getName() + " " + awardWinner.getSurname());
        }
        return winner;
    }



    private Person awardWinner(Person personDetails) {
        BigDecimal giftAmount = new BigDecimal("5000.00");
        personDetails.accruesBalance(giftAmount);
        personRepository.save(personDetails);
        return personDetails;
    }
}
