package com.example.city_management.mapper;

import com.example.city_management.dto.CarInsuranceDTO;
import com.example.city_management.entity.CarInsurance;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarInsuranceMapper {

    CarInsuranceMapper INSTANCE = Mappers.getMapper(CarInsuranceMapper.class);


    CarInsurance toEntity(CarInsuranceDTO carInsuranceDTO);


    CarInsuranceDTO toDto(CarInsurance carInsurance);

}
