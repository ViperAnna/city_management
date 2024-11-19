package com.example.city_management.mapper;

import com.example.city_management.dto.PassportDTO;
import com.example.city_management.entity.Passport;
import org.hibernate.Hibernate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PassportMapper {
    PassportMapper INSTANCE = Mappers.getMapper(PassportMapper.class);


    Passport toEntity(PassportDTO passportDTO);

//    @Mapping(target = "numberOfPassport",
//    source = "numberOfPassport")
//    default PassportDTO toDto(Passport passport){
//        if(passport == null){
//            return null;
//        }
//        Hibernate.initialize(passport);
//        return PassportMapper.INSTANCE.toDto(passport);
//    }

    @Mapping(target = "numberOfPassport",
            source = "numberOfPassport")
     PassportDTO toDto(Passport passport);

}
