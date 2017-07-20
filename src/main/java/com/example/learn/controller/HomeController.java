package com.example.learn.controller;


import com.example.learn.model.Person;
import com.example.learn.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private PersonService personService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("tekst", "test test nowy tekst");
        return "index";
    }

    @GetMapping("/get")
    @ResponseBody
    public List<Person> getPersonList() throws InterruptedException {
        return personService.findAll();
    }

    @GetMapping("/add")
    @ResponseBody
    public Person add() throws InterruptedException {
        return personService.addPerson(new Person("Vader", 2.5));
    }

    @GetMapping("/refresh")
    @ResponseBody
    public boolean refresh() {
        return personService.refresh();
    }
}