package com.example.city_management.controller;

import com.example.city_management.dto.PassportDTO;
import com.example.city_management.repository.PassportRepository;
import com.example.city_management.service.Impl.PassportServiceImpl;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passports")
@RequiredArgsConstructor
public class PassportController {
    private final PassportServiceImpl passportServiceImpl;
    private final PassportRepository passportRepository;

    @GetMapping
    public ResponseEntity<List<PassportDTO>> getAllPassports() {
        List<PassportDTO> passportDto = passportServiceImpl.getAllPassports();
        return ResponseEntity
                .ok(passportDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PassportDTO> getPassportByPersonId(@PathVariable Long id) {
        PassportDTO person = passportServiceImpl.getPassportByPersonId(id);
        return ResponseEntity
                .ok(person);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PassportDTO> updatePassport(@PathVariable Long id, @RequestBody PassportDTO passportDetails) {
        PassportDTO passportDTO = passportServiceImpl.updatePassport(id, passportDetails);
        return ResponseEntity
                .ok(passportDTO);
    }

    @GetMapping("/male/{firstLetter}")
    public ResponseEntity<List<PassportDTO>> getPassportsOfMalesByFirstLetter(@PathVariable String firstLetter) {
        List<PassportDTO> passports = passportRepository.getPassportsOfMalesByFirstLetter(firstLetter);
        return ResponseEntity
                .ok(passports);
    }
}

