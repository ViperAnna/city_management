package com.example.city_management.service.Impl;

import com.example.city_management.dto.InternationalPassportDTO;
import com.example.city_management.entity.InternationalPassport;
import com.example.city_management.entity.Person;
import com.example.city_management.exception.InternationalPassportNotFoundException;
import com.example.city_management.exception.PersonNotFoundException;
import com.example.city_management.mapper.InternationalPassportMapper;
import com.example.city_management.repository.InternationalPassportRepository;
import com.example.city_management.repository.PersonRepository;
import com.example.city_management.service.InternationalPassportService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InternationalPassportServiceImpl implements InternationalPassportService {
    private final InternationalPassportRepository internationalPassportRepo;

    private final RestTemplate restTemplate;

    private final PersonRepository personRepository;

    @Override
    public InternationalPassportDTO createInternationalPassport(Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() ->
                        new PersonNotFoundException("Person not found with id: " + id));
        if(person.getBalance().compareTo(BigDecimal.valueOf(5000.0))){
            throw new RuntimeException("Not Balance");
        }

    }

    public List<InternationalPassportDTO> getAllInternationalPassports(){
        List<InternationalPassport> interPassList = (List<InternationalPassport>) internationalPassportRepo.findAll();
        return interPassList.stream()
                .map(InternationalPassportMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    public InternationalPassportDTO getInternationalPassportByPersonId(Long id){
        InternationalPassport interPass = internationalPassportRepo.findById(id)
                .orElseThrow(() ->
                        new InternationalPassportNotFoundException("International passport not found with id: " + id));
        return InternationalPassportMapper.INSTANCE.toDto(interPass);
    }


    @Transactional
    public InternationalPassportDTO updateInternationalPassport(Long id, @NonNull InternationalPassportDTO passportDetails){
        InternationalPassport internationalPassport = internationalPassportRepo.findById(id)
                .orElseThrow(() ->
                        new InternationalPassportNotFoundException("International passport not found with id: " + id));
        internationalPassport.setNumberOfInternationalPassport(passportDetails.getNumberOfInternationalPassport());
        return  InternationalPassportMapper.INSTANCE.toDto(internationalPassportRepo.save(internationalPassport));
    }


}
