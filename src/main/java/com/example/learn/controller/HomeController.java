package com.example.learn.controller;


import com.example.learn.model.Greeting;
import com.example.learn.model.Person;
import com.example.learn.service.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private PersonRepository personRepository;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("tekst", "test test nowy tekst");
        return "index";
    }

    @GetMapping("/data")
    @CrossOrigin
    @ResponseBody
    public List<Person> getPersonList() throws InterruptedException {
        log.info("Request for PersonService data");
        // Thread.sleep(2000);
        return personRepository.findAll();
    }

    @GetMapping("/data/{id}")
    @CrossOrigin
    @ResponseBody
    public Person getPerson(@PathVariable("id") long id) {
        return personRepository.findOne(id);
    }

    @PostMapping("/send")
    @ResponseBody
    public Greeting postData(@RequestBody(required = false) Greeting greeting) {
        if (greeting != null) {
            greeting.setAge(greeting.getAge() + 10);
            return greeting;
        }
        else
            return new Greeting();
    }

    @GetMapping("/hello")
    @ResponseBody
    public String getHello() {
        return "hello";
    }
}