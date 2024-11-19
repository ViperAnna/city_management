package com.example.city_management.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    private String street;
    private Integer numberOfHouse;
    private Long zipCode;
}





