package com.example.city_management.repository;

import com.example.city_management.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
    @Query("SELECT DISTINCT p FROM Person p JOIN p.houses h WHERE h.address.street LIKE :street")
    List<Person> getAllOwnersByStreet(@Param("street") String street);


}






