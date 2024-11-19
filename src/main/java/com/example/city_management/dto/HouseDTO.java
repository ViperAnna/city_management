package com.example.city_management.dto;

import com.example.city_management.entity.Person;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class HouseDTO {
    private Long id;
    private AddressDTO address;

//    @JsonManagedReference
    private Set<PersonDTO> owners;
}
