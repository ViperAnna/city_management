package com.example.city_management.controller;

import com.example.city_management.dto.InternationalPassportDTO;
import com.example.city_management.dto.PassportDTO;
import com.example.city_management.repository.PassportRepository;
import com.example.city_management.service.Impl.InternationalPassportServiceImpl;
import com.example.city_management.service.Impl.PassportServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/international-passports")
@RequiredArgsConstructor
public class InternationalPassportController {
private  final InternationalPassportServiceImpl interPassServiceImpl;



    @GetMapping
    public ResponseEntity<List<InternationalPassportDTO>> getAllInternationalPassports() {
        List<InternationalPassportDTO> passports = interPassServiceImpl.getAllInternationalPassports();
        return ResponseEntity
                .ok(passports);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InternationalPassportDTO> getInternationalPassportByPersonId(@PathVariable Long id) {
        InternationalPassportDTO passport = interPassServiceImpl.getInternationalPassportByPersonId(id);
        return ResponseEntity
                .ok(passport);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InternationalPassportDTO> updateInternationalPassport(@PathVariable Long id, @RequestBody InternationalPassportDTO passportDetails) {
        InternationalPassportDTO passport = interPassServiceImpl.updateInternationalPassport(id, passportDetails);
        return ResponseEntity
                .ok(passport);
    }


}
