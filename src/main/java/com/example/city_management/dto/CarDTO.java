package com.example.city_management.dto;

import com.example.city_management.entity.CarInsurance;
import com.example.city_management.entity.Person;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CarDTO {
    private Long id;
    private String brand;

//    @JsonManagedReference
    private PersonDTO owner;

    private CarInsuranceDTO carInsurance;
}
