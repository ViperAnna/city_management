package com.example.city_management.mapper;

import com.example.city_management.dto.InternationalPassportDTO;
import com.example.city_management.dto.PassportDTO;
import com.example.city_management.entity.InternationalPassport;
import com.example.city_management.entity.Passport;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InternationalPassportMapper {

    InternationalPassportMapper INSTANCE = Mappers.getMapper(InternationalPassportMapper.class);


    InternationalPassport toEntity(InternationalPassportDTO InternationalPassportDTO);


    InternationalPassportDTO toDto(InternationalPassport internationalPassport);

}

