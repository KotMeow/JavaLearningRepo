package com.example.learn.model.projections;


import com.example.learn.model.Food;
import com.example.learn.model.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;


@Projection(name = "personDetails", types = {Person.class})
public interface PersonDetails {

    String getName();
    double getPower();
    List<Food> getFood();
}
