package com.example.city_management.controller;

import com.example.city_management.dto.PersonDTO;
import com.example.city_management.entity.Person;
import com.example.city_management.mapper.PersonMapper;
import com.example.city_management.service.Impl.LotteryServiceIml;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
//@AllArgsConstructor
@RequiredArgsConstructor
@RequestMapping("/lottery")
public class LotteryController {


    private final LotteryServiceIml lotteryServiceIml;

    @PostMapping("/start")
    public ResponseEntity<PersonDTO> startLottery() {
        PersonDTO winner = PersonMapper.INSTANCE.toDto(lotteryServiceIml.startLottery());

        if(winner != null){
            return ResponseEntity
                    .ok(winner);
        }
        else{
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
    }
    }
}
