package com.example.learn.service;


import com.example.learn.model.Person;

import java.util.List;

public interface PersonRepositoryCustom {

    List<Person> findWithMinimumPowerOf(double lowest);
}
