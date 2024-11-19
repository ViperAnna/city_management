package com.example.city_management.dto;

import com.example.city_management.entity.Car;
import com.example.city_management.entity.Passport;
import com.example.city_management.entity.Person;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CarInsuranceDTO implements Serializable {

   private Long id;

   private String policyNumber;

   private PersonDTO owner;

   private PassportDTO numberPassOfOwner;

   private CarDTO car;

   private LocalDate startDate;

   private LocalDate endDate;
}