package com.example.city_management.mapper;

import com.example.city_management.dto.CarDTO;
import com.example.city_management.entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarMapper {
    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    Car toEntity(CarDTO carDTO);

    CarDTO toDto(Car car);

}
