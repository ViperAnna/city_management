package com.example.city_management.mapper;

import com.example.city_management.dto.HouseDTO;
import com.example.city_management.entity.House;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HouseMapper {
    HouseMapper INSTANCE = Mappers.getMapper(HouseMapper.class);

    House toEntity(HouseDTO houseDTO);

    HouseDTO toDto(House house);

}
