package com.example.learn.controller;


import com.example.learn.model.Person;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;

@RestController
public class HomeController {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;


    @PostMapping("/send")
    public Person postData(@RequestBody(required = false) Person person) throws InterruptedException {
        Gson gson = new Gson();
        String json = gson.toJson(person);
        jmsMessagingTemplate.convertAndSend(this.queue, json);
        return person;
    }
}