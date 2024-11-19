package com.example.city_management.service.Impl;

import com.example.city_management.dto.PassportDTO;
import com.example.city_management.entity.Passport;
import com.example.city_management.exception.PassportNotFoundException;
import com.example.city_management.mapper.PassportMapper;
import com.example.city_management.repository.PassportRepository;
import com.example.city_management.service.PassportService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class PassportServiceImpl implements PassportService {
    private final PassportRepository passportRepository;

    public List<PassportDTO> getAllPassports() {
        List<Passport> passportList = (List<Passport>) passportRepository.findAll();
        return passportList.stream()
                .map(PassportMapper.INSTANCE::toDto)
                .toList();
    }

    public PassportDTO getPassportByPersonId(Long id) {
        Passport passport = passportRepository.findById(id)
                .orElseThrow(() ->
                        new PassportNotFoundException("Passport not found with id: " + id));
        return PassportMapper.INSTANCE.toDto(passport);
    }

    @Transactional
    public PassportDTO updatePassport(Long id, @NonNull PassportDTO passportDetails) {
        Passport passport = passportRepository.findById(id)
                .orElseThrow(() ->
                        new PassportNotFoundException("Passport not found with id: " + id));
        passport.setNumberOfPassport(passportDetails.getNumberOfPassport());
        return PassportMapper.INSTANCE.toDto(passportRepository.save(passport));
    }
}
