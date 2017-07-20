package com.example.learn.service;


import com.example.learn.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PersonRepository personRepository;

    @Cacheable(value = "person")
    public List<Person> findAll() throws InterruptedException {
        Thread.sleep(2000L);
        return personRepository.findAll();
    }


    //nie updatuje cache?
    @CachePut(value = "person", key = "#person")
    public Person addPerson(Person person) throws InterruptedException {
        Thread.sleep(2000L);
        personRepository.save(person);
        log.info(String.valueOf(person));
        return person;
    }

    @CacheEvict(value = "person", allEntries = true)
    public boolean refresh() {
        return true;
    }
}
