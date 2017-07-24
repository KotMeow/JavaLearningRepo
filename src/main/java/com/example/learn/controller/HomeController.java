package com.example.learn.controller;


import com.example.learn.model.Food;
import com.example.learn.model.Person;
import com.example.learn.service.FoodRepository;
import com.example.learn.service.PersonRepository;
import com.google.firebase.auth.FirebaseAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger log = LoggerFactory.getLogger(HomeController.class);

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

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public void check(@RequestBody ReqUser reqUser, HttpServletRequest request) {
        log.info(String.valueOf(reqUser));
        log.info(request.getHeader("X-Firebase-Auth"));
//        FirebaseAuth.getInstance().verifyIdToken(request.getHeader("X-Firebase-Auth"))
//                .addOnSuccessListener(decodedToken -> {
//                    String uid = decodedToken.getUid();
//                    System.out.println(uid);
//                })
//        .addOnFailureListener(e -> System.out.println("Fail FAIL"));
    }

    @GetMapping("/api/msg")
    public String getProtectedMsg() {
        log.info("protected");
        return "Protected";
    }
    @GetMapping("/public/msg")
    public String getMsg() {
        log.info("public");
        return "Unprotected";
    }
}