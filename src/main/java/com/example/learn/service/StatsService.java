package com.example.learn.service;

import com.example.learn.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class StatsService {


    private final Logger log = LoggerFactory.getLogger(StatsService.class);
    @Autowired
    private PersonRepository personRepository;

    @Async
    public void calculate(Person person) throws InterruptedException {
        log.info("Async start");

        Thread.sleep(2000);

        person.setPower(person.getPower() * 10);
        personRepository.save(person);

        log.info("Async finish");
    }
}
