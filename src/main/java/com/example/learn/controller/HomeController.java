package com.example.learn.controller;


import com.example.learn.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class HomeController {

    @Autowired
    private JmsTemplate jmsTemplate;


    @PostMapping("/send")
    public Person postData(@RequestBody(required = false) Person person) throws InterruptedException {
        log.info("started sending");
        jmsTemplate.setDeliveryDelay(TimeUnit.MINUTES.toMillis(1));
        jmsTemplate.convertAndSend("sample", person);
        return person;
    }
}