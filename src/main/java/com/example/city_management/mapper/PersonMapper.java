package com.example.city_management.mapper;

import com.example.city_management.dto.PersonDTO;
import com.example.city_management.entity.Passport;
import com.example.city_management.entity.Person;
import com.example.city_management.entity.enumeration.Gender;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(uses = PassportMapper.class)
public interface PersonMapper {
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "surname", target = "surname")
//    @Mapping(source = "gender", target = "gender")
    @Mapping(source = "gender", target = "gender", qualifiedByName = "stringToGender")
//    @Mapping(source = "numberOfPassport", target = "passport", qualifiedByName = "longToPassport")
    @Mapping(source = "balance", target = "balance")
    Person toEntity(PersonDTO personDTO);

    //    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "surname", target = "surname")
//    @Mapping(source = "gender", target = "gender")
    @Mapping(source = "gender", target = "gender", qualifiedByName = "genderToString")
    @Mapping(source = "passport", target = "numberOfPassport")
//    @Mapping(source = "passport", target = "numberOfPassport", qualifiedByName = "passportToLong")
    @Mapping(source = "balance", target = "balance")
    PersonDTO toDto(Person person);

    @Named("stringToGender")
    default Gender stringToGender(String gender) {
        if (gender == null) {
            return null;
        }
        return Gender.valueOf(gender.toLowerCase());

    }
    @Named("genderToString")
    default String genderToString(Gender gender){
        if (gender == null) {
            return null;
        }
        return switch (gender) {
            case male -> "male";
            case female -> "female";
            default -> throw new IllegalArgumentException("Unknown gender: " + gender);
        };
    }
//    default Long passportToLong(Passport passport){
//        return  passport != null ? passport.getNumberOfPassport() : null;
//    }
//    default Passport longToPassport(Long number){
//        return  number != null ? new Passport(number) : null;
//    }
}
