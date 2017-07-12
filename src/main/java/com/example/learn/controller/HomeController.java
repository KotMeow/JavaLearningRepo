package com.example.learn.controller;


import com.example.learn.model.Person;
import com.example.learn.service.PersonRepository;
import com.example.learn.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
class HomeController {

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("tekst", "test test nowy tekst");
        return "index";
    }

    @GetMapping("/data")
    @CrossOrigin(origins = "http://localhost:8081")
    @ResponseBody
    public List<Person> getData() throws InterruptedException {
        log.info("Request for PersonService data");
        // Thread.sleep(2000);

        return personRepository.findAll();
    }
}