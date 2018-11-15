package com.example.learn.service;

import com.example.learn.model.Greeting;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class GreetingsService implements BaseService<Greeting> {

    public Greeting getCalculated(Greeting greeting) {
        greeting.setDateOfBirth(calculateSomething(greeting));
        return greeting;
    }

    @Override
    public int calculateSomething(Greeting greeting) {
        return LocalDate.now().getYear() - greeting.getAge();
    }

    @Override
    public Greeting modifySomething(Greeting greeting) {
        return null;
    }
}
