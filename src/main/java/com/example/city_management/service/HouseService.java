package com.example.city_management.service;

import com.example.city_management.dto.HouseDTO;

import java.util.List;

public interface HouseService {
    HouseDTO createHouse(HouseDTO houseDetails);

    HouseDTO getHouseById(Long id);

    List<HouseDTO> getAllHouses();

    HouseDTO updateHouse(Long id, HouseDTO houseDetails);

    void deleteHouseById(Long id);
}
