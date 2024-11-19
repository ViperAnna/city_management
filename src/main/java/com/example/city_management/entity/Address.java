package com.example.city_management.entity;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Embeddable
@Access(AccessType.PROPERTY)
public class Address {

    @NotNull
    @Column(nullable = false, length = 30)
    private String street;


    @Column(name = "number_of_house", length = 5)
    private Integer numberOfHouse;

    @Column(name = "zip_code", nullable = false, length = 10)
    private Long zipCode;
}
