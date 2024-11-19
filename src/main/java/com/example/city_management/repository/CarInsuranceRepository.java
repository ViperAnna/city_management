package com.example.city_management.repository;

import com.example.city_management.entity.CarInsurance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarInsuranceRepository extends CrudRepository<CarInsurance, Long> {
}
