package com.example.city_management.service;

import com.example.city_management.dto.CarDTO;
import com.example.city_management.dto.CarInsuranceDTO;
import lombok.NonNull;


import java.util.List;

public interface CarInsuranceService {

    CarInsuranceDTO createCarInsurance(@NonNull CarInsuranceDTO carDetails);

    findCarInsuranceByOwnerId();


    CarInsuranceDTO getCarInsureById(Long id);


    List<CarInsuranceDTO> getAllCarInsurances();

    List<CarDTO> getAllCarInsurancesByOwner(Long id);


    CarInsuranceDTO updateCarInsurance(Long id, @NonNull CarInsuranceDTO carDetails);


    public void deleteCarInsuranceById(Long id);
}
