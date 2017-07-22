package com.example.learn.controller;


import com.example.learn.model.Food;
import com.example.learn.model.Person;
import com.example.learn.service.FoodRepository;
import com.example.learn.service.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RestController
@CrossOrigin
public class HomeController {


    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private FoodRepository foodRepository;


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Person addPerson(@RequestBody Person person) {
        System.out.print(person);
        personRepository.save(person);
        return person;
    }


    @RequestMapping(value = "/addfood", method = RequestMethod.POST)
    public void addFoodToPerson(@RequestBody AssignReq assignReq) {
        Person person = personRepository.findOne(assignReq.getIdperson());
        Food food = foodRepository.findOne(assignReq.getIdfood());
        food.getPeople().add(person);
        person.getFood().add(food);

        personRepository.save(person);
    }
}