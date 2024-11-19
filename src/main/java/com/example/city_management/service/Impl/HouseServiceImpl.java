package com.example.city_management.service.Impl;

import com.example.city_management.dto.AddressDTO;
import com.example.city_management.dto.HouseDTO;
import com.example.city_management.dto.PersonDTO;
import com.example.city_management.entity.Address;
import com.example.city_management.entity.House;
import com.example.city_management.entity.Person;
import com.example.city_management.exception.HouseNotFoundException;
import com.example.city_management.mapper.HouseMapper;
import com.example.city_management.mapper.PersonMapper;
import com.example.city_management.repository.HouseRepository;
import com.example.city_management.repository.PersonRepository;
import com.example.city_management.service.HouseService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class HouseServiceImpl implements HouseService {

    private final PersonRepository personRepository;
    private final HouseRepository houseRepository;

    @Override
    @Transactional
    public HouseDTO createHouse(@NonNull HouseDTO houseDetails) {
        House house = new House();
        Address address = setNewAddress(houseDetails.getAddress());
        house.setAddress(address);
        return HouseMapper.INSTANCE.toDto(houseRepository.save(house));
    }

    private @NonNull Address setNewAddress(@NonNull AddressDTO addressDetails) {
        Address address = new Address();
        address.setStreet(addressDetails.getStreet());

        if (addressDetails.getNumberOfHouse() != null) {
            address.setNumberOfHouse(addressDetails.getNumberOfHouse());
        }
        if (addressDetails.getZipCode() != null) {
            address.setZipCode(addressDetails.getZipCode());
        }
        return address;
    }

    @Override
    public HouseDTO getHouseById(Long id) {
        House house = houseRepository.findById(id)
                .orElseThrow(() -> new HouseNotFoundException("House not found with id: " + id));
        for(Person owner: house.getOwners()){
            Hibernate.initialize(owner.getPassport());
        }
        return HouseMapper.INSTANCE.toDto(house);
    }

    @Override
    public List<HouseDTO> getAllHouses() {
        List<House> house = (List<House>) houseRepository.findAll();
        return house.stream()
                .map(HouseMapper.INSTANCE::toDto)
                .toList();
    }

    @Override
    @Transactional
    public HouseDTO updateHouse(Long id, @NonNull HouseDTO houseDetails) {
        House house = houseRepository.findById(id)
                .orElseThrow(() -> new HouseNotFoundException("House not found with id: " + id));

        if (houseDetails.getAddress() != null) {
            updateAddress(house.getAddress(), houseDetails.getAddress());
        }

        if (houseDetails.getOwners() != null) {
            Set<Person> persons = houseDetails.getOwners().stream()
                            .map(PersonMapper.INSTANCE::toEntity)
                                    .collect(Collectors.toSet());
            house.setOwners(persons);
        }
        return HouseMapper.INSTANCE.toDto(houseRepository.save(house));
    }

    private void updateAddress(Address currentAddress, @NonNull AddressDTO newAddress) {
        if (newAddress.getStreet() != null) {
            currentAddress.setStreet(newAddress.getStreet());
        }
        if (newAddress.getNumberOfHouse() != null) {
            currentAddress.setNumberOfHouse(newAddress.getNumberOfHouse());
        }
        if (newAddress.getZipCode() != null) {
            currentAddress.setZipCode(newAddress.getZipCode());
        }
    }

    @Override
    @Transactional
    public void deleteHouseById(Long id) {
        House house = houseRepository.findById(id)
                .orElseThrow(() -> new HouseNotFoundException("House not found with id: " + id));
        personRepository.deleteAll(house.getOwners());
        houseRepository.delete(house);
    }
}










