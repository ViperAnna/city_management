package com.example.city_management.service.Impl;

import com.example.city_management.dto.CarDTO;
import com.example.city_management.dto.CarInsuranceDTO;
import com.example.city_management.entity.Car;
import com.example.city_management.entity.CarInsurance;
import com.example.city_management.entity.Person;
import com.example.city_management.exception.CarInsuranceNotFoundException;
import com.example.city_management.exception.CarNotFoundException;
import com.example.city_management.exception.PersonNotFoundException;
import com.example.city_management.mapper.CarInsuranceMapper;
import com.example.city_management.mapper.CarMapper;
import com.example.city_management.mapper.PassportMapper;
import com.example.city_management.mapper.PersonMapper;
import com.example.city_management.repository.CarInsuranceRepository;
import com.example.city_management.service.CarInsuranceService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class CarInsuranceServiceImpl implements CarInsuranceService {

    private final CarInsuranceRepository insuranceRepo;


    @Override
    public CarInsuranceDTO createCarInsurance(@NonNull CarInsuranceDTO carDetails) {
       //        Car car = carRepository.findById(carId)
//                .orElseThrow(() -> new RuntimeException("Car not found"));

        CarInsurance insurance = new CarInsurance();
        insurance.setPolicyNumber(carDetails.getPolicyNumber());
        insurance.setOwner(PersonMapper.INSTANCE.toEntity(carDetails.getOwner()));
        insurance.setNumberPassOfOwner(PassportMapper.INSTANCE.toEntity(carDetails.getNumberPassOfOwner()));
        insurance.setCar(CarMapper.INSTANCE.toEntity(carDetails.getCar()));
        insurance.setStartDate(LocalDate.now());
        insurance.setStartDate(LocalDate.now().plusYears(1));

        return CarInsuranceMapper.INSTANCE.toDto(insuranceRepo.save(insurance));
    }

    @Override
    public List<CarInsuranceDTO> getAllCarInsurances() {
        List<CarInsurance> insurances = (List<CarInsurance>) insuranceRepo.findAll();
        return insurances.stream()
                .map(CarInsuranceMapper.INSTANCE::toDto)
                .toList();
    }

    @Override
    public List<CarDTO> getAllCarInsurancesByOwner(Long id) {
        List<CarInsurance> insurances = insuranceRepo.findCarInsuranceByOwnerId(id);
        return insurances.stream()
                .map(CarInsuranceMapper.INSTANCE::toDto)
                .toList();
    }

    @Override
    @Transactional
    public CarInsuranceDTO updateCarInsurance(Long id, @NonNull CarInsuranceDTO carDetails) {
        CarInsurance insurance = insuranceRepo.findById(id)
                .orElseThrow(() -> new CarInsuranceNotFoundException("Car insurance not found with id: " + id));
        if (carDetails.getPolicyNumber() != null) {
            insurance.setPolicyNumber(carDetails.getPolicyNumber());
        }
//
//        if (carDetails.getOwner() != null && carDetails.getOwner().getId() != null) {
//            Long ownerId = carDetails.getOwner().getId();
//            Person person = personRepository.findById(ownerId)
//                    .orElseThrow(() -> new PersonNotFoundException("Person not found with id: " + id));
//            car.setOwner(person);
//        }
        return CarInsuranceMapper.INSTANCE.toDto(insurance);
    }

    @Override
    @Transactional
    public void deleteCarInsuranceById(Long id) {
        insuranceRepo.findById(id)
                .orElseThrow(() -> new PersonNotFoundException("Person not found with id: " + id));
        insuranceRepo.deleteById(id);
    }
}
