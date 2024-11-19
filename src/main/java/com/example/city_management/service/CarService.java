package com.example.city_management.service;

import com.example.city_management.dto.CarDTO;

import java.util.List;

public interface CarService {
    CarDTO createCar(CarDTO carDetails);

    List<CarDTO> getAllCars();

    CarDTO getCarById(Long id);

    CarDTO updateCar(Long id, CarDTO carDetails);

    void deleteCarById(Long id);

//    void deleteAllCars();
}
