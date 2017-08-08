package com.example.learn.service;

import com.example.learn.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StatsService {

    @Autowired
    private PersonRepository personRepository;

    @JmsListener(destination = "sample")
    public void calculate(Person person) throws InterruptedException {

        log.info("Saving: " + person);

        personRepository.save(person);

    }
}
