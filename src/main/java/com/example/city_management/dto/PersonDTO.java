package com.example.city_management.dto;

import com.example.city_management.entity.Car;
import com.example.city_management.entity.House;
import com.example.city_management.entity.InternationalPassport;
import com.example.city_management.entity.enumeration.Gender;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {
    private Long id;
    private String name;
    private String surname;
    private String gender;

    @JsonManagedReference
    private PassportDTO numberOfPassport;

    private InternationalPassportDTO internationalPassport;

    private BigDecimal balance;


    ////    @JsonBackReference
//    @JsonIgnore
//    @JsonManagedReference
//    private List<CarInsuranceDTO> listOfInsurances;


//    private Set<HouseDTO> houses;
//
//    @JsonIgnore
////    @JsonBackReference
//    private Set<CarDTO> cars;

    public Long getNumberOfPassport(){
        return numberOfPassport != null ? numberOfPassport.getNumberOfPassport() : null;
    }

}
