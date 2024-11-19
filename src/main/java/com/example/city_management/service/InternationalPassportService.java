package com.example.city_management.service;

import com.example.city_management.dto.InternationalPassportDTO;
import com.example.city_management.entity.InternationalPassport;
import com.example.city_management.exception.InternationalPassportNotFoundException;
import com.example.city_management.mapper.InternationalPassportMapper;
import lombok.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

public interface InternationalPassportService {

    InternationalPassportDTO createInternationalPassport(Long id);
     List<InternationalPassportDTO> getAllInternationalPassports();

     InternationalPassportDTO getInternationalPassportByPersonId(Long id);


     InternationalPassportDTO updateInternationalPassport(Long id, @NonNull InternationalPassportDTO passportDetails);

}
