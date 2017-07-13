package com.example.learn.service;

import com.example.learn.model.Person;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


public class PersonRepositoryImpl implements PersonRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Person> findWithMinimumPowerOf(double lowest) {
        return entityManager.createQuery("SELECT p from Person p where p.power >= :lowest", Person.class)
                .setParameter("lowest", lowest).getResultList();
    }
}
