package com.example.city_management.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.validation.constraints.Size;
import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PassportDTO {
    private Long id;
    //    @Size(max = 6)
    private Long numberOfPassport;

    @JsonBackReference
    private PersonDTO owner;
}
