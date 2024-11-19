package com.example.city_management.service;

import com.example.city_management.dto.PassportDTO;

import java.util.List;

public interface PassportService {
    List<PassportDTO> getAllPassports();

    PassportDTO getPassportByPersonId(Long id);

    PassportDTO updatePassport(Long id, PassportDTO passportDetails);
}
