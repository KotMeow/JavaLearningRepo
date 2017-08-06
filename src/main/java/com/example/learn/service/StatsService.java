package com.example.learn.service;

import com.example.learn.model.Person;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class StatsService {

    @Autowired
    private PersonRepository personRepository;

    private final Logger log = LoggerFactory.getLogger(StatsService.class);


    @JmsListener(destination = "sample")
    public void calculate(String personJson) throws InterruptedException {
        Gson gson = new Gson();
        Person person = gson.fromJson(personJson, Person.class);

        log.info("Async start " + person);

        Thread.sleep(2000);
        personRepository.save(person);

        log.info("Async finish");
    }
}
