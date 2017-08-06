package com.example.learn.controller;


import com.example.learn.model.Person;
import com.example.learn.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {


    @Autowired
    private StatsService statsService;


    @PostMapping("/send")
    public Person postData(@RequestBody(required = false) Person person) throws InterruptedException {
        statsService.calculate(person);
        return person;
    }
}