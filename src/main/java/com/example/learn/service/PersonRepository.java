package com.example.learn.service;

import com.example.learn.model.Person;
import com.example.learn.model.PersonProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface PersonRepository extends JpaRepository<Person, Long>, PersonRepositoryCustom {

    List<Person> findByName(String s);
    List<PersonProjection> findAllProjectedBy();

    @Query("select max(p.power) from Person p")
    Double findMaxPower();

    Integer getAvgAge();
}
