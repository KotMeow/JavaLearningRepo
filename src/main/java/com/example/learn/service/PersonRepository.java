package com.example.learn.service;

import com.example.learn.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Repository
@Transactional
public interface PersonRepository extends JpaRepository<Person, Long> {

    int countByCountry(String country);

    @Query("select p from Person p where p.country = ?1 and p.power between ?2 and ?3")
    List<Person> queryByCountryAndPowerRange(String country, double min, double max);

    List<Person> findAllByOrderByPowerDesc();

    List<Person> findByCountryOrCity(String country, String city);

    List<Person> findByCountryAndCity(String country, String city);

    List<Person> findByCountryNot(String country);

    List<Person> findByCountryContaining(String country);

    List<Person> findByPowerGreaterThan(double power);

    List<Person> findByBirthDayAfter(LocalDate date);

    List<Person> findByCountryIn(Collection<String> countries);

    List<Person> findByFoodName(String name);

    List<Person> findByFoodNameIn(Collection<String> names);
}
