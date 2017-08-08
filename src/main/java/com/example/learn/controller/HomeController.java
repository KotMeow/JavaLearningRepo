package com.example.learn.controller;


import com.example.learn.model.Person;
import com.google.gson.Gson;
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
    private JmsTemplate jmsMessagingTemplate;


    @PostMapping("/send")
    public Person postData(@RequestBody(required = false) Person person) throws InterruptedException {
        Gson gson = new Gson();
        String json = gson.toJson(person);
        log.info("started sending");
        jmsMessagingTemplate.setDeliveryDelay(TimeUnit.MINUTES.toMillis(1));
        jmsMessagingTemplate.convertAndSend("sample", json);
        return person;
    }
}