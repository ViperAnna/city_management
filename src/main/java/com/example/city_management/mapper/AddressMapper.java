package com.example.city_management.mapper;

import com.example.city_management.dto.AddressDTO;
import com.example.city_management.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    Address toEntity(AddressDTO addressDTO);

    AddressDTO toDto(Address address);

}
