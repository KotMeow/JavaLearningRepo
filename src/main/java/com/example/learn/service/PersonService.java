package com.example.learn.service;

import com.example.learn.model.Person;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PersonService {

    public List<Person> getPersonsList() {
        return Arrays.asList(
                new Person("Yoda", 24, 4.44),
                new Person("R2D2", 74, 8.0),
                new Person("Sith", 44, 2.0),
                new Person("Vader", 55, 9.5),
                new Person("Leia", 55, 7.0),
                new Person("Luke", 88, 7.0));
    }
}
