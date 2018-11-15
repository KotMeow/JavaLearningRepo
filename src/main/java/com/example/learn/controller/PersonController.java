package com.example.learn.controller;

import com.example.learn.model.Greeting;
import com.example.learn.model.Person;
import com.example.learn.service.GreetingsService;
import com.example.learn.service.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private GreetingsService greetingsService;

    @Autowired
    private PersonRepository personRepository;

    @PostMapping("/greeting")
    public Greeting getPerson(@RequestBody Greeting greeting) {
        return greetingsService.getCalculated(greeting);
    }

    @PostMapping
    public Person savePerson(@RequestBody Person person) {
        return personRepository.save(person);
    }
}
