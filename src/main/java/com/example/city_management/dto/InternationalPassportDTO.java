package com.example.city_management.dto;

import com.example.city_management.entity.Person;
import com.example.city_management.entity.enumeration.Gender;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InternationalPassportDTO {

    private Long id;

    private String numberOfInternationalPassport;

    @JsonBackReference
    private PersonDTO owner;
}